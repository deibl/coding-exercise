package com.example.demo.adapter.restclient;

import com.example.demo.domain.model.Currency;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collection;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;

@Service
public class CurrencyParser {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  public Collection<Currency> allCurrenciesFrom(String responseJson) {
    try {
      JsonNode root = MAPPER.readTree(responseJson);
      return StreamSupport
          .stream(Spliterators.spliteratorUnknownSize(root.get("symbols").fields(), Spliterator.ORDERED), false)
          .map(entry -> new Currency(entry.getKey(), entry.getValue().asText()))
          .collect(Collectors.toList());
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public float conversionRateFrom(String responseJson) {
    try {
      JsonNode root = MAPPER.readTree(responseJson);
      return (float) root.get("rates").elements().next().asDouble();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
