package com.epam.crypto.adviser.model;

import java.util.Set;
import lombok.Builder;
import lombok.Value;
import org.apache.commons.csv.CSVRecord;

@Value
@Builder
public class CSVRecordValidationContext {

  CSVRecord csvRecord;
  Set<String> mandatoryFields;
  Set<String> notBlankFields;

}
