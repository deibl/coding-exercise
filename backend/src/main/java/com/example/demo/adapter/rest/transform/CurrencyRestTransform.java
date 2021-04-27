package com.example.demo.adapter.rest.transform;

import com.example.demo.adapter.rest.model.CurrencyDto;
import com.example.demo.domain.model.Currency;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyRestTransform {

  CurrencyDto from(Currency currency);

}
