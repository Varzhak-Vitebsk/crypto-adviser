package com.epam.crypto.adviser.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;
import java.math.BigDecimal;
import java.time.Instant;

@Value
@Builder
public class CryptoDescription {
  @Schema(name = "timestamp", description = "Price timestamp", example = "1641009600000")
  Instant timestamp;
  @Schema(name = "price", description = "Price", example = "46813.03")
  BigDecimal price;
}
