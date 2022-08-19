package com.epam.crypto.adviser.service.validator;

import com.epam.crypto.adviser.model.api.CryptoDescriptionRequest;
import org.springframework.stereotype.Service;

@Service
public class DescriptionRequestValidator implements Validator<CryptoDescriptionRequest> {

  @Override
  public boolean isValid(CryptoDescriptionRequest source) {
    return source.getDateFrom().isBefore(source.getDateTo());
  }
}
