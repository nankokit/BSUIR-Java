package com.nankokit.phonebook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nankokit.phonebook.service.CountryService;
import com.nankokit.phonebook.service.Idd;

@RestController
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/country-to-code")
    public ResponseEntity<Idd> getCode(@RequestParam("country") String country) {
        Idd idd = countryService.getCode(country);
        return new ResponseEntity<>(idd, HttpStatus.OK);
    }

}
