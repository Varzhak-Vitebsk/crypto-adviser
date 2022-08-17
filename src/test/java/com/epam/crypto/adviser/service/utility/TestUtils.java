package com.epam.crypto.adviser.service.utility;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.ResourceUtils;
import java.io.FileReader;
import java.io.Reader;

@UtilityClass
public class TestUtils {

  @SneakyThrows
  public static Iterable<CSVRecord> loadResource(Reader reader) {
    return CSVFormat.RFC4180.builder().setHeader().build().parse(reader);
  }

  @SneakyThrows
  public static Reader reader(String classpath) {
    return new FileReader(ResourceUtils.getFile(classpath));
  }

}
