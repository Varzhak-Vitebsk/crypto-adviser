package com.epam.crypto.adviser.model.api;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NormalizedCrypto {

  String crypto;
  Instant minPriceTimestamp;
  Instant maxPriceTimestamp;
  BigDecimal normalizedPrice;
}
