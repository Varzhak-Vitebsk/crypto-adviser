package com.epam.crypto.adviser.model.api;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NormalizedCryptosResponse {

  List<NormalizedCrypto> cryptos;

}
