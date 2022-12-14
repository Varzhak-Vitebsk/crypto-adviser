package com.epam.crypto.adviser.service.parser;

import com.epam.crypto.adviser.exception.CsvParseException;
import com.epam.crypto.adviser.model.CSVRecordParsingContext;

public interface CSVRecordParser<T> {

  T parse(CSVRecordParsingContext parsingContext) throws CsvParseException;

}
