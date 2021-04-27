package com.example.demo.adapter.restclient;

import com.example.demo.domain.model.Currency;
import com.example.demo.domain.port.out.CurrencyRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class CurrencyClient implements CurrencyRepository {

  private static final String GET_CURRENCIES_URL = "http://data.fixer.io/api/symbols?access_key=%s";
  private static final String GET_CURRENCIES_CONVERSION_URL = "http://data.fixer.io/api/latest?base=%s&symbols=%s&access_key=%s";

  private final RestTemplate restTemplate;
  private final CurrencyParser currencyParser;
  private final String currencyApiKey;

  CurrencyClient(RestTemplate restTemplate, CurrencyParser currencyParser,
      @Value("${currency.api.key}") String currencyApiKey) {
    this.restTemplate = restTemplate;
    this.currencyParser = currencyParser;
    this.currencyApiKey = currencyApiKey;
  }

  @Override
  @Cacheable("currencies")
  public Collection<Currency> getAllCurrencies() {
    final ResponseEntity<String> response = restTemplate
        .getForEntity(String.format(GET_CURRENCIES_URL, currencyApiKey), String.class);
    return currencyParser.allCurrenciesFrom(response.getBody());
  }

  @Override
  public float getConversionRate(Currency currency, Currency othCurrency) {
    final ResponseEntity<String> response = restTemplate.getForEntity(String
        .format(GET_CURRENCIES_CONVERSION_URL, currency.getAbbreviation(), othCurrency.getAbbreviation(),
            currencyApiKey), String.class);
    return currencyParser.conversionRateFrom(response.getBody());
  }
}
