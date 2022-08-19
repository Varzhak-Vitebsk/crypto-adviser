package com.epam.crypto.adviser.service.loader;

import org.springframework.web.multipart.MultipartFile;

public interface DataLoader {

  void uploadData(MultipartFile file);

}
