package com.exercise.impl.controller;

import com.exercise.impl.cache.CountryCache;
import com.exercise.impl.dto.PhoneNumberState;
import com.exercise.impl.dto.RequestParams;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.exercise.impl.service.CustomerService;

@CrossOrigin(origins = {"http://localhost:9090", "http://localhost:3000", "http://localhost:80", "http://127.0.0.1:9090", "http://127.0.0.1:3000"})
@RestController
public class CustomerController {
    @Autowired
    CustomerService service;
    @Autowired
    CountryCache countryCache;
    private RequestParams requestParams;

    @GetMapping("/countries")
    private ResponseEntity<?> getAllCountries() throws JSONException {
        return new ResponseEntity(service.getAllCountries(), HttpStatus.OK);
    }

    @GetMapping("/phone-numbers")
    private ResponseEntity<?> getAllCustomerPhoneNumber(@RequestParam(name = "country_code", required = false) String countryCode,
                                                        @RequestParam(name = "is_valid", required = false) PhoneNumberState isValid) {
        requestParams = new RequestParams();
        requestParams.setCountryCode(countryCode);
        requestParams.setState(isValid);
        return new ResponseEntity<>(service.getAllPhoneNumbers(requestParams), HttpStatus.OK);
    }
}