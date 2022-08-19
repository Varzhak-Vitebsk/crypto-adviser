package com.epam.crypto.adviser.service.validator;

import com.epam.crypto.adviser.model.api.NormalizedCryptosRequest;
import org.springframework.stereotype.Service;

@Service
public class NormalizationRequestValidator implements Validator<NormalizedCryptosRequest> {

  @Override
  public boolean isValid(NormalizedCryptosRequest source) {
    return source.getDateFrom().isBefore(source.getDateTo());
  }
}
