package com.epam.crypto.adviser.storage.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "crypto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Builder
public class CryptoEntity extends BaseEntity {

  /**
   * Unique id
   */
  @NotNull
  @Id
  @SequenceGenerator(name = "crypto_id_gen", sequenceName = "crypto_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "crypto_id_gen")
  private Long id;

  /**
   * Crypto's name
   */
  @NotBlank
  private String name;

  /**
   * Crypto's support status
   */
  private boolean supported;

  /**
   * Crypto's priced history
   */
  @Valid
  @NotNull
  @OneToMany(mappedBy = "crypto", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private Set<PriceEntity> prices = new HashSet<>();

}
