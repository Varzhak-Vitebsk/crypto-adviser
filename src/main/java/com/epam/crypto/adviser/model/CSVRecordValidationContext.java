package com.epam.crypto.adviser.model;

import lombok.Builder;
import lombok.Value;
import org.apache.commons.csv.CSVRecord;
import java.util.Set;

@Value
@Builder
public class CSVRecordValidationContext {

  CSVRecord csvRecord;
  Set<String> mandatoryFields;
  Set<String> notBlankFields;

}
