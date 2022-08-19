package com.epam.crypto.adviser.model.api;

import io.swagger.v3.oas.annotations.Parameter;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class CryptoDescriptionRequest {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @Parameter(name = "dateFrom", description = "Left border of a range")
  @NotNull
  private LocalDate dateFrom;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @Parameter(name = "dateTo", description = "Right border of a range")
  @NotNull
  private LocalDate dateTo;

}
