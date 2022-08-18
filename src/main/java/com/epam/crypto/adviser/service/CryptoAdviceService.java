package com.epam.crypto.adviser.service;

import com.epam.crypto.adviser.exception.NotSupportedCryptoException;
import com.epam.crypto.adviser.exception.PriceNotFoundException;
import com.epam.crypto.adviser.mapper.NormalizedCryptoMapper;
import com.epam.crypto.adviser.mapper.PriceDtoToCryptoDescriptionMapper;
import com.epam.crypto.adviser.model.api.CryptoDescription;
import com.epam.crypto.adviser.model.api.CryptoDescriptionRequest;
import com.epam.crypto.adviser.model.api.CryptoDescriptionResponse;
import com.epam.crypto.adviser.model.api.NormalizedCrypto;
import com.epam.crypto.adviser.model.api.NormalizedCryptosRequest;
import com.epam.crypto.adviser.model.api.NormalizedCryptosResponse;
import com.epam.crypto.adviser.property.PriceProperties;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CryptoAdviceService {

  private final CryptoRepositoryService cryptoRepositoryService;
  private final PriceRepositoryService priceRepositoryService;
  private final NormalizedCryptoMapper normalizedCryptoMapper;
  private final PriceDtoToCryptoDescriptionMapper cryptoDescriptionMapper;
  private final PriceProperties priceProperties;

  public NormalizedCryptosResponse getNormalizedCryptos(NormalizedCryptosRequest request) {
    var supportedCryptos = cryptoRepositoryService.getSupportedCryptos();
    List<NormalizedCrypto> normalizedCryptos = new ArrayList<>();
    for (var crypto : supportedCryptos) {
      var from = localDateToUTCInstant(request.getDateFrom());
      var to = localDateToUTCInstant(request.getDateTo());
      var maxPrice = priceRepositoryService.getMaxPriceInRangeForCrypto(crypto, from, to);
      var minPrice = priceRepositoryService.getMinPriceInRangeForCrypto(crypto, from, to);
      if (maxPrice.isEmpty() || minPrice.isEmpty()) {
        log.info("There is no {} prices between {} - {}", crypto.getName(), request.getDateFrom(), request.getDateTo());
        continue;
      }
      normalizedCryptos.add(normalizedCryptoMapper.map(minPrice.get(), maxPrice.get(), priceProperties.getScale()));
    }
    return NormalizedCryptosResponse.builder()
        .cryptos(normalizedCryptos.stream().sorted(Comparator.comparing(NormalizedCrypto::getNormalizedPrice)).toList())
        .build();
  }

  public CryptoDescriptionResponse getCrypto(String cryptoName, CryptoDescriptionRequest request) {
    var crypto = cryptoRepositoryService.getSupportedCryptos().stream()
        .filter(c -> cryptoName.equals(c.getName()))
        .findAny()
        .orElseThrow(() -> new NotSupportedCryptoException("Crypto %s not supported!", cryptoName));

    var from = localDateToUTCInstant(request.getDateFrom());
    var to = localDateToUTCInstant(request.getDateTo());

    var maxPrice = priceRepositoryService.getMaxPriceInRangeForCrypto(crypto, from, to)
        .map(cryptoDescriptionMapper::map);
    var minPrice = priceRepositoryService.getMinPriceInRangeForCrypto(crypto, from, to)
        .map(cryptoDescriptionMapper::map);
    var oldestPrice = priceRepositoryService.getOldestPriceInRangeFroCrypto(crypto, from, to)
        .map(cryptoDescriptionMapper::map);
    var newestPrice = priceRepositoryService.getNewestPriceInRangeFroCrypto(crypto, from, to)
        .map(cryptoDescriptionMapper::map);

    if (maxPrice.isEmpty() || minPrice.isEmpty() || oldestPrice.isEmpty() || newestPrice.isEmpty()) {
      throw new PriceNotFoundException("Not found price for crypto %s", cryptoName);
    }

    return CryptoDescriptionResponse.builder()
        .crypto(cryptoName)
        .max(maxPrice.get())
        .min(minPrice.get())
        .oldest(oldestPrice.get())
        .newest(newestPrice.get())
        .build();
  }

  public NormalizedCrypto getBestCryptoForTheDate(LocalDate date) {
    return NormalizedCrypto.builder().build();
  }

  private Instant localDateToUTCInstant(LocalDate date) {
    return date.atStartOfDay().toInstant(ZoneOffset.UTC);
  }

  private CryptoDescription makeEmpty() {
    return CryptoDescription.builder().build();
  }


}
