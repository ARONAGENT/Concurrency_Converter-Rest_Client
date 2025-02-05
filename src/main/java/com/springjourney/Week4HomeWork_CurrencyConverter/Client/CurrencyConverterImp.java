package com.springjourney.Week4HomeWork_CurrencyConverter.Client;

import com.springjourney.Week4HomeWork_CurrencyConverter.Configuration.CurrencyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CurrencyConverterImp implements CurrencyConverter{


    private final RestClient restClient;

    public CurrencyConverterImp(RestClient restClient){
        this.restClient = restClient;
    }

    public CurrencyResponse INRToUSD(String fromCurrency , String toCurrency, double units){
        final String API_URL = "https://api.freecurrencyapi.com/v1/latest";
        final String API_KEY = "fca_live_fVvX7Ova9HDTDJ3TJRMPYVkgYSBqgQsZxyrelesa";
        String url = API_URL + "?apikey=" + API_KEY
                + "&currencies=" + toCurrency
                + "&base_currency=" + fromCurrency;
        return restClient.get()
                .uri(url) // Pass the constructed URL
                .retrieve()
                .body(CurrencyResponse.class);

    }

}
