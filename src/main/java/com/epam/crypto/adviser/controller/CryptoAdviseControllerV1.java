package com.epam.crypto.adviser.controller;

import com.epam.crypto.adviser.model.ErrorResponse;
import com.epam.crypto.adviser.model.api.CryptoDescriptionRequest;
import com.epam.crypto.adviser.model.api.CryptoDescriptionResponse;
import com.epam.crypto.adviser.model.api.NormalizedCrypto;
import com.epam.crypto.adviser.model.api.NormalizedCryptosRequest;
import com.epam.crypto.adviser.model.api.NormalizedCryptosResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@RequestMapping(path = "/api/public/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public interface CryptoAdviseControllerV1 {

  @Operation(summary = "Acquire normalized list of cryptos in descending order for a period.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", content = {
          @Content(mediaType = "application/json",
              schema = @Schema(implementation = NormalizedCryptosResponse.class))
      }),
      @ApiResponse(responseCode = "400", content = {
          @Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))
      }),
      @ApiResponse(responseCode = "500", content = {
          @Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))
      })
  })
  @GetMapping(value = "normalization")
  ResponseEntity<NormalizedCryptosResponse> getNormalizedCryptos(
      @ParameterObject @Valid NormalizedCryptosRequest request);

  @Operation(summary = "Acquire specific crypto description (oldest/newest/min/max) for a period.")

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", content = {
          @Content(mediaType = "application/json",
              schema = @Schema(implementation = CryptoDescriptionResponse.class))
      }),
      @ApiResponse(responseCode = "400", content = {
          @Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))
      }),
      @ApiResponse(responseCode = "404", content = {
          @Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))
      }),
      @ApiResponse(responseCode = "500", content = {
          @Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))
      })
  })
  @GetMapping(value = "{crypto}/description")
  ResponseEntity<CryptoDescriptionResponse> getBestCrypto(@PathVariable String crypto,
      @ParameterObject @Valid CryptoDescriptionRequest request);

  @Operation(summary = "Acquire crypto with highest normalization value for a specific day.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", content = {
          @Content(mediaType = "application/json",
              schema = @Schema(implementation = CryptoDescriptionResponse.class))
      }),
      @ApiResponse(responseCode = "400", content = {
          @Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))
      }),
      @ApiResponse(responseCode = "404", content = {
          @Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))
      }),
      @ApiResponse(responseCode = "500", content = {
          @Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))
      })
  })
  @GetMapping(value = "highest/normalized/crypto")
  ResponseEntity<NormalizedCrypto> getBestCrypto(
      @Parameter(name = "date", description = "Day for normalization") @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

}
