package com.epam.crypto.adviser.controller;

import com.epam.crypto.adviser.exception.InvalidRequestException;
import com.epam.crypto.adviser.model.api.CryptoDescriptionRequest;
import com.epam.crypto.adviser.model.api.CryptoDescriptionResponse;
import com.epam.crypto.adviser.model.api.NormalizedCrypto;
import com.epam.crypto.adviser.model.api.NormalizedCryptosRequest;
import com.epam.crypto.adviser.model.api.NormalizedCryptosResponse;
import com.epam.crypto.adviser.service.CryptoAdviceService;
import java.time.LocalDate;
import com.epam.crypto.adviser.service.validator.DescriptionRequestValidator;
import com.epam.crypto.adviser.service.validator.NormalizationRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CryptoAdviseControllerV1Impl implements CryptoAdviseControllerV1 {

  private final CryptoAdviceService service;
  private final NormalizationRequestValidator normalizationRequestValidator;
  private final DescriptionRequestValidator descriptionRequestValidator;

  @Override
  public ResponseEntity<NormalizedCryptosResponse> getNormalizedCryptos(NormalizedCryptosRequest request) {
    if (normalizationRequestValidator.isInvalid(request)) {
      throw new InvalidRequestException("Normalization request is invalid!");
    }
    return new ResponseEntity<>(service.getNormalizedCryptos(request), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CryptoDescriptionResponse> getBestCrypto(String crypto,
      CryptoDescriptionRequest request) {
    if (descriptionRequestValidator.isInvalid(request)) {
      throw new InvalidRequestException("Description request is invalid!");
    }
    return new ResponseEntity<>(service.getCrypto(crypto, request), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<NormalizedCrypto> getBestCrypto(LocalDate date) {
    return new ResponseEntity<>(service.getBestCryptoForTheDate(date), HttpStatus.OK);
  }

}
