package com.epam.crypto.adviser.model;

import java.time.Instant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {

  @Schema(name = "timestamp", description = "Error timestamp")
  Instant timestamp;
  @Schema(name = "status", description = "Error code")
  int status;
  @Schema(name = "error", description = "Error message")
  String error;
  @Schema(name = "path", description = "Endpoint")
  String path;
}

