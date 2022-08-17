package com.epam.crypto.adviser.model.api;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CryptoDescriptionRequest {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @Parameter(name = "dateFrom", description = "Left border of a range")
  private LocalDate dateFrom;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @Parameter(name = "dateTo", description = "Right border of a range")
  private LocalDate dateTo;

}
