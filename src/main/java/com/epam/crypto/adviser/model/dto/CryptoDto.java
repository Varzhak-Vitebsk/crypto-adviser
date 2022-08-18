package com.epam.crypto.adviser.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CryptoDto {

  String name;
  boolean supported;

}
