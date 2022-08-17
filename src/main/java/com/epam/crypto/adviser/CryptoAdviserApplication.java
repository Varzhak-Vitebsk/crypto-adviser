package com.epam.crypto.adviser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "com.epam.crypto.adviser")
public class CryptoAdviserApplication {

  public static void main(String[] args) {
    SpringApplication.run(CryptoAdviserApplication.class, args);
  }

}
