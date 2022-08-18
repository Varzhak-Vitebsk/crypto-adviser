package com.epam.crypto.adviser.model.dto;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PriceDto {

  CryptoDto crypto;
  Instant priceTimestamp;
  BigDecimal price;

}
