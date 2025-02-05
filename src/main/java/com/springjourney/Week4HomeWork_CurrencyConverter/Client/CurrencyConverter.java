package com.springjourney.Week4HomeWork_CurrencyConverter.Client;

import com.springjourney.Week4HomeWork_CurrencyConverter.Configuration.CurrencyResponse;

public interface CurrencyConverter {

    CurrencyResponse INRToUSD(String fromCurrency, String toCurrency, double units);
}
