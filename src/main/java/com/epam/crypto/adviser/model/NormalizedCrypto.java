package com.epam.crypto.adviser.model;

import lombok.Builder;
import lombok.Value;
import java.math.BigDecimal;

@Value
@Builder
public class NormalizedCrypto {

  String crypto;
  BigDecimal normalizedPrice;
}
