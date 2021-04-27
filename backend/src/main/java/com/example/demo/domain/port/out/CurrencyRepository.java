package com.example.demo.domain.port.out;

import com.example.demo.domain.model.Currency;
import java.util.Collection;

public interface CurrencyRepository {

  Collection<Currency> getAllCurrencies();

  float getConversionRate(Currency currency, Currency otherCurrency);

}
