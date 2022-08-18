package com.epam.crypto.adviser.controller;

import com.epam.crypto.adviser.model.ErrorResponse;
import com.epam.crypto.adviser.model.api.CryptoDescriptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(path = "/api/private/v1/upload/from/csv", produces = MediaType.APPLICATION_JSON_VALUE)
public interface CSVUploadControllerV1 {

  @Operation(summary = "Upload information about crypto pricing using *.csv file.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Uploaded", content = {
          @Content(mediaType = "application/json",
              schema = @Schema(implementation = CryptoDescriptionResponse.class))
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
  @PostMapping(value = "crypto/price")
  ResponseEntity<Void> uploadCryptoPrices(@RequestParam("file") MultipartFile file);

  @Operation(summary = "Upload / update information about cryptos using *.csv file.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Uploaded", content = {
          @Content(mediaType = "application/json",
              schema = @Schema(implementation = CryptoDescriptionResponse.class))
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
  @PostMapping(value = "crypto")
  ResponseEntity<Void> uploadCryptos(@RequestParam("file") MultipartFile file);

}
