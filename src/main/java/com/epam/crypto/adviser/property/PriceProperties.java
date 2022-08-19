package com.epam.crypto.adviser.property;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@ConfigurationProperties("price")
@Validated
public class PriceProperties {

  @NotNull
  @Positive
  private Integer scale = 4;
}
