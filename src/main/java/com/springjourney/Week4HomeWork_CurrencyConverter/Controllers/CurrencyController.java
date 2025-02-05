package com.springjourney.Week4HomeWork_CurrencyConverter.Controllers;

import com.springjourney.Week4HomeWork_CurrencyConverter.Client.CurrencyConverter;
import com.springjourney.Week4HomeWork_CurrencyConverter.Configuration.CurrencyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {
    @Autowired
    CurrencyConverter currencyConverter;
    @Autowired
    CurrencyResponse cr;

    @GetMapping("/convertCurrency")
    public double convertCurrency(
            @RequestParam String fromCurrency,
            @RequestParam String toCurrency,
            @RequestParam double units) {

        // Map the JSON response to CurrencyResponse object
        cr = currencyConverter.INRToUSD(fromCurrency, toCurrency, units);
        double c =cr.getData().get("USD");
        double result = c*units;
        return result;
    }

}
