package com.epam.crypto.adviser.service;

import com.epam.crypto.adviser.mapper.PriceEntityToPriceDtoMapper;
import com.epam.crypto.adviser.model.dto.PriceDto;
import com.epam.crypto.adviser.storage.PriceRepository;
import com.epam.crypto.adviser.storage.model.CryptoEntity;
import com.epam.crypto.adviser.storage.model.PriceEntity;
import java.time.Instant;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceRepositoryService {

  private final PriceRepository repository;
  private final PriceEntityToPriceDtoMapper mapper;

  public void save(PriceEntity entityUpdate) {
    repository.save(entityUpdate);
  }

  public Optional<PriceDto> getMaxPriceInRangeForCrypto(CryptoEntity crypto, Instant from, Instant to) {
    return repository.findFirstByCryptoAndPriceTimestampBetween(crypto, from, to, Sort.by("price").descending())
        .map(mapper::map);

  }

  public Optional<PriceDto> getMinPriceInRangeForCrypto(CryptoEntity crypto, Instant from, Instant to) {
    return repository.findFirstByCryptoAndPriceTimestampBetween(crypto, from, to, Sort.by("price").ascending())
        .map(mapper::map);
  }

  public Optional<PriceDto> getOldestPriceInRangeFroCrypto(CryptoEntity crypto, Instant from, Instant to) {
    return repository.findFirstByCryptoAndPriceTimestampBetween(crypto, from, to, Sort.by("priceTimestamp").ascending())
        .map(mapper::map);
  }

  public Optional<PriceDto> getNewestPriceInRangeFroCrypto(CryptoEntity crypto, Instant from, Instant to) {
    return repository.findFirstByCryptoAndPriceTimestampBetween(crypto, from, to,
            Sort.by("priceTimestamp").descending())
        .map(mapper::map);
  }

}
