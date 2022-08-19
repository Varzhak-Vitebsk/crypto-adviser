package com.epam.crypto.adviser.model.api;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NormalizedCryptosResponse {

  @Schema(name = "cryptos", description = "Normalized values")
  List<NormalizedCrypto> cryptos;

}
