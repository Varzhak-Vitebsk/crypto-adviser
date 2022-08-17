package com.epam.crypto.adviser.controller;

import com.epam.crypto.adviser.model.CryptoDescriptionRequest;
import com.epam.crypto.adviser.model.CryptoDescriptionResponse;
import com.epam.crypto.adviser.model.NormalizedCrypto;
import com.epam.crypto.adviser.model.NormalizedCryptosRequest;
import com.epam.crypto.adviser.model.NormalizedCryptosResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@RequestMapping(path = "/api/public/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public interface CryptoAdviseControllerV1 {

  @Operation(summary = "Acquire normalized list of cryptos in descending order for a period.")
  @GetMapping(value = "normalization")
  ResponseEntity<NormalizedCryptosResponse> getNormalizedCryptos(@ParameterObject NormalizedCryptosRequest request);

  @Operation(summary = "Acquire specific crypto description (oldest/newest/min/max) for a period.")
  @GetMapping(value = "{crypto}/description")
  ResponseEntity<CryptoDescriptionResponse> getCryptoDescription(@PathVariable String crypto,
                                                                 @ParameterObject CryptoDescriptionRequest request);

  @Operation(summary = "Acquire crypto with highest normalization value for a specific day.")
  @GetMapping(value = "highest/normalized/crypto")
  ResponseEntity<NormalizedCrypto> getCryptoDescription(
      @Parameter(name = "date", description = "Day for normalization") @NotNull LocalDate date);

}