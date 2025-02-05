package com.springjourney.Week4HomeWork_CurrencyConverter.Configuration;

import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class CurrencyResponse {

        private Map<String, Double> data;

        // Getters and setters
        public Map<String, Double> getData() {
            return data;
        }

        public void setData(Map<String, Double> data) {
            this.data = data;
        }
}
