package com.Currency.currencyrateapi.currency.controller;

import com.Currency.currencyrateapi.currency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
@RequestMapping(path = "UniTech/CurrencyRateAPI/currRate")
public class CurrencyController {
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @GetMapping(value = "/properties/{val1}/{val2}")
    public ResponseEntity<Double> checkCurr(@PathVariable String val1, @PathVariable String val2) throws ParserConfigurationException, IOException, SAXException {
        return ResponseEntity.status(HttpStatus.OK).body(currencyService.checkCurrency(val1,val2));
    }
}
