package com.epam.crypto.adviser.service;

import com.epam.crypto.adviser.model.CSVRecordValidationContext;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import java.util.Set;

public class CSVRecordValidator implements Validator<CSVRecordValidationContext> {

  @Override
  public boolean isValid(CSVRecordValidationContext source) {
    return areFieldsPresent(source.getCsvRecord(), source.getMandatoryFields())
        && areFieldsNotBlank(source.getCsvRecord(), source.getNotBlankFields());
  }

  private boolean areFieldsPresent(CSVRecord source, Set<String> fieldNames) {
    for (var fieldName : fieldNames) {
      if (!source.getParser().getHeaderNames().contains(fieldName)) {
        return false;
      }
    }
    return true;
  }

  private boolean areFieldsNotBlank(CSVRecord source, Set<String> fieldNames) {
    for (var fieldName : fieldNames) {
      if (StringUtils.isBlank(source.get(fieldName))) {
        return false;
      }
    }
    return true;
  }
}
