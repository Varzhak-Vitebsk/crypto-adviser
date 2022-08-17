package com.epam.crypto.adviser.controller;

import com.epam.crypto.adviser.model.api.NormalizedCrypto;
import com.epam.crypto.adviser.model.api.NormalizedCryptosRequest;
import com.epam.crypto.adviser.model.api.NormalizedCryptosResponse;
import com.epam.crypto.adviser.model.api.CryptoDescriptionRequest;
import com.epam.crypto.adviser.model.api.CryptoDescriptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

@RestController
public class CryptoAdviseControllerV1Impl implements CryptoAdviseControllerV1 {

  @Override
  public ResponseEntity<NormalizedCryptosResponse> getNormalizedCryptos(NormalizedCryptosRequest request) {
    return new ResponseEntity<>(NormalizedCryptosResponse.builder().build(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CryptoDescriptionResponse> getCryptoDescription(String crypto,
      CryptoDescriptionRequest request) {
    return new ResponseEntity<>(CryptoDescriptionResponse.builder().build(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<NormalizedCrypto> getCryptoDescription(LocalDate date) {
    return new ResponseEntity<>(NormalizedCrypto.builder().build(), HttpStatus.OK);
  }
}
