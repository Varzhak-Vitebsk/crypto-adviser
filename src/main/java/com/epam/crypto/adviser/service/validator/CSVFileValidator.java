package com.epam.crypto.adviser.service.validator;

import com.epam.crypto.adviser.utility.CSVUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVFileValidator implements Validator<MultipartFile> {

  @Override
  public boolean isValid(MultipartFile source) {
    return CSVUtils.hasCSVFormat(source);
  }
}
