package com.example.demo.domain.port.in;

import com.example.demo.domain.model.Currency;
import java.util.Collection;

public interface CurrencyUseCase {

  Collection<Currency> getAllCurrencies();

}
