package com.epam.crypto.adviser.service;

import com.epam.crypto.adviser.exception.CsvParseException;
import com.epam.crypto.adviser.model.CSVRecordParsingContext;
import com.epam.crypto.adviser.model.CSVRecordValidationContext;
import com.epam.crypto.adviser.storage.model.CryptoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CryptoEntityParser implements CSVRecordParser<CryptoEntity> {

  private static final String NAME_FIELD = "Name";
  private static final String SUPPORTED_STATUS_FIELD = "Supported";
  private static final Set<String> MANDATORY_FIELDS = Set.of(NAME_FIELD, SUPPORTED_STATUS_FIELD);
  private final CSVRecordValidator recordValidator;
  @Override
  public CryptoEntity parse(CSVRecordParsingContext parsingContext) {

    var source = parsingContext.getSource();

    if (recordValidator.isInvalid(makeContext(source))) {
      throw new CsvParseException("One of mandatory or not blank fields is absent or blank!");
    }

    return CryptoEntity.builder()
        .name(source.get(NAME_FIELD))
        .supported(Boolean.parseBoolean(source.get(SUPPORTED_STATUS_FIELD)))
        .prices(Set.of())
        .build();
  }

  private CSVRecordValidationContext makeContext(CSVRecord source) {
    return CSVRecordValidationContext.builder()
        .csvRecord(source)
        .mandatoryFields(MANDATORY_FIELDS)
        .notBlankFields(MANDATORY_FIELDS)
        .build();
  }
}
