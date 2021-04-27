package com.example.demo.domain.service;

import com.example.demo.domain.model.Currency;
import com.example.demo.domain.port.in.CurrencyUseCase;
import com.example.demo.domain.port.out.CurrencyRepository;
import java.util.Collection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CurrencyService implements CurrencyUseCase {

  private static final Currency DEFAULT_CURRENCY = new Currency("EUR", "Euro");

  private final CurrencyRepository currencyRepository;

  @Override
  public Collection<Currency> getAllCurrencies() {
    return currencyRepository.getAllCurrencies();
  }

  public boolean currencyNeedsToBeConverted(Currency currency) {
    return !DEFAULT_CURRENCY.equals(currency);
  }

  public Currency getDefaultCurrency() {
    return DEFAULT_CURRENCY;
  }

  public float convertToDefaultCurrency(float price, Currency otherCurrency) {
    final float conversionRate = currencyRepository.getConversionRate(getDefaultCurrency(), otherCurrency);
    return price / conversionRate;
  }
}
