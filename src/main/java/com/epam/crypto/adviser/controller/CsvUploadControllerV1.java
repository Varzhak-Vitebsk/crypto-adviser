package com.epam.crypto.adviser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(path = "/api/private/v1/upload/from/csv", produces = MediaType.APPLICATION_JSON_VALUE)
public interface CsvUploadControllerV1 {

  @Operation(summary = "Upload information about crypto currency using *.csv file.")
  @ApiResponse(responseCode = "200", description = "Uploaded successfully.")
  @PostMapping(value = "crypto")
  ResponseEntity<Void> uploadCryptoValues(@RequestParam("file") MultipartFile file);

}
