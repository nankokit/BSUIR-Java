package com.nankokit.phonebook.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryService {
    private static final String REST_COUNTRY_URL = "https://restcountries.com/v3.1/";

    private CountryService() {
    }

    public Idd getCode(String countryName) {
        RestTemplate restTemplate = new RestTemplate();
        String url = REST_COUNTRY_URL + "name/{countryName}?fields=idd";
        HashMap<String, String> urlParams = new HashMap<>();
        urlParams.put("countryName", countryName);

        ResponseEntity<Country[]> response = restTemplate.getForEntity(url, Country[].class, urlParams);
        Country[] countries = response.getBody();
        if (countries != null && countries.length > 0) {
            return countries[0].getIdd();
        } else {
            return null;
        }
    }
}
