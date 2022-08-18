package com.epam.crypto.adviser.storage.model;

import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "price")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceEntity extends BaseEntity {

  /**
   * Unique id
   */
  @NotNull
  @Id
  @SequenceGenerator(name = "price_id_gen", sequenceName = "price_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "price_id_gen")
  private Long id;

  /**
   * Crypto
   */
  @Valid
  @ManyToOne(fetch = FetchType.LAZY)
  @Cascade(CascadeType.ALL)
  private CryptoEntity crypto;

  /**
   * Price timestamp
   */
  @NotNull
  private Instant priceTimestamp;

  /**
   * Price for one unit in USD
   */
  @NotNull
  private BigDecimal price;

}
