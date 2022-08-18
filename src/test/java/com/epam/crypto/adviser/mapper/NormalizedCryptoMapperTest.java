package com.epam.crypto.adviser.mapper;

import com.epam.crypto.adviser.model.api.NormalizedCrypto;
import com.epam.crypto.adviser.model.dto.CryptoDto;
import com.epam.crypto.adviser.model.dto.PriceDto;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

class NormalizedCryptoMapperTest {

  private static final String CRYPTO_NAME = "BTC";
  private static final BigDecimal MAX_PRICE = BigDecimal.valueOf(100.6D);
  private static final BigDecimal MIN_PRICE = BigDecimal.valueOf(54.456D);
  private static final int SCALE = 4;

  private final NormalizedCryptoMapper mapper = new NormalizedCryptoMapperImpl();

  @Test
  void map_EqualsToExample() {
    //given min amd max prices
    var min = priceDto(MIN_PRICE);
    var max = priceDto(MAX_PRICE);
    //and example
    var example = NormalizedCrypto.builder()
        .crypto(CRYPTO_NAME)
        .normalizedPrice(MAX_PRICE.subtract(MIN_PRICE).setScale(SCALE, RoundingMode.HALF_DOWN)
            .divide(MIN_PRICE, RoundingMode.HALF_DOWN))
        .build();

    //then result equals to example
    assertThat(example).usingRecursiveComparison().isEqualTo(mapper.map(min, max, SCALE));

  }

  private PriceDto priceDto(BigDecimal price) {
    return PriceDto.builder()
        .crypto(cryptoDto())
        .price(price)
        .build();
  }

  private CryptoDto cryptoDto() {
    return CryptoDto.builder()
        .name(CRYPTO_NAME)
        .build();
  }
}