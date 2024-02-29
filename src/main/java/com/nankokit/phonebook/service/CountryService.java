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

    public String[] getPhoneCode(String countryName) {
        RestTemplate restTemplate = new RestTemplate();
        String url = REST_COUNTRY_URL + "name/{countryName}?fields=idd";
        HashMap<String, String> urlParams = new HashMap<>();
        urlParams.put("countryName", countryName);

        ResponseEntity<Country[]> response = restTemplate.getForEntity(url, Country[].class, urlParams);
        if (response.getStatusCode().is2xxSuccessful()) {
            Country[] countries = response.getBody();
            if (countries != null && countries.length > 0) {
                int i = 0;
                String[] answer = new String[countries[0].getIdd().getSuffixes().length];
                while (i < countries[0].getIdd().getSuffixes().length) {
                    answer[i] = countries[0].getIdd().getRoot() + countries[0].getIdd().getSuffixes()[i];
                    i++;
                }
                return answer;
            }
        }
        return new String[0];
    }
}
