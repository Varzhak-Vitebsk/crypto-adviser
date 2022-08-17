package com.epam.crypto.adviser.service;

import com.epam.crypto.adviser.exception.CsvParseException;
import org.apache.commons.csv.CSVRecord;

public interface CSVRecordParser<T> {

  T parse(CSVRecord source) throws CsvParseException;

}
