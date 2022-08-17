package com.epam.crypto.adviser.model.api;

import lombok.Builder;
import lombok.Value;
import java.util.List;

@Value
@Builder
public class NormalizedCryptosResponse {

  List<NormalizedCrypto> cryptos;

}
