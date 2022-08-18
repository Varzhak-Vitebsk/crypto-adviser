package com.epam.crypto.adviser.utility;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

@UtilityClass
public class CSVUtils {

  public static final String TYPE = "text/csv";

  public static boolean hasCSVFormat(MultipartFile file) {
    return TYPE.equals(file.getContentType());
  }

  public static boolean hasNotCSVFormat(MultipartFile file) {
    return !hasCSVFormat(file);
  }

  public static BigDecimal parseStringToBigDecimal(String value) {
    return BigDecimal.valueOf(Double.parseDouble(value));
  }

  public static Instant parseStringToInstant(String value) {
    return Instant.ofEpochMilli(Long.parseLong(value));
  }
}
