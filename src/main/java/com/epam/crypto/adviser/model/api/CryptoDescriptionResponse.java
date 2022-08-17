package com.epam.crypto.adviser.model.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CryptoDescriptionResponse {
  @Schema(name = "crypto", description = "Crypto name", example = "BTC")
  String crypto;
  @Schema(name = "oldest", description = "Oldest crypto values")
  CryptoDescription oldest;
  @Schema(name = "newest", description = "Newest crypto values")
  CryptoDescription newest;
  @Schema(name = "max", description = "Max crypto values")
  CryptoDescription max;
  @Schema(name = "min", description = "Min crypto values")
  CryptoDescription min;
}
