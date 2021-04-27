package com.example.demo.adapter.rest;

import com.example.demo.adapter.rest.model.CurrencyDto;
import com.example.demo.adapter.rest.transform.CurrencyRestTransform;
import com.example.demo.domain.port.in.CurrencyUseCase;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost"})
public class CurrencyController {

  private final CurrencyRestTransform currencyRestTransform;
  private final CurrencyUseCase currencyUseCase;

  @GetMapping("/currencies")
  public ResponseEntity<Collection<CurrencyDto>> getAllCurrencies() {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(
            currencyUseCase.getAllCurrencies()
                .stream()
                .map(currencyRestTransform::from)
                .collect(Collectors.toList())
        );
  }
}
