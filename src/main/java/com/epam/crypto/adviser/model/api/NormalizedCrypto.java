package com.epam.crypto.adviser.model.api;

import lombok.Builder;
import lombok.Value;
import java.math.BigDecimal;

@Value
@Builder
public class NormalizedCrypto {

  String crypto;
  BigDecimal normalizedPrice;
}
