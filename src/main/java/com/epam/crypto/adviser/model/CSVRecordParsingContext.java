package com.epam.crypto.adviser.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.csv.CSVRecord;

@Getter
@SuperBuilder
public abstract class CSVRecordParsingContext {

  private final CSVRecord source;

}

