package com.nankokit.phonebook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nankokit.phonebook.service.CountryService;

@RestController
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/country-to-code")
    public String getCountryCode(@RequestParam("country") String country) {
        String[] code = countryService.getPhoneCode(country);
        StringBuilder result = new StringBuilder();
        result.append("The phone code(s) for country ");
        result.append(country);
        result.append(": ");
        for (int i = 0; i < code.length; i++) {
            result.append(code[i]);
            result.append(" ");
        }
        return result.toString();
    }
}
