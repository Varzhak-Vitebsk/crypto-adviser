package com.epam.crypto.adviser.model.api;

import java.math.BigDecimal;
import java.time.Instant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NormalizedCrypto {

  @Schema(name = "crypto", description = "Crypto name", example = "BTC")
  String crypto;
  @Schema(name = "minPriceTimestamp", description = "Min price timestamp", example = "1641009600000")
  Instant minPriceTimestamp;
  @Schema(name = "maxPriceTimestamp", description = "Max price timestamp", example = "1641009600000")
  Instant maxPriceTimestamp;
  @Schema(name = "normalizedPrice", description = "Normalized price", example = "0.1235")
  BigDecimal normalizedPrice;
}
