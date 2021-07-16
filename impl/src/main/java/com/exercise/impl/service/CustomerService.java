package com.exercise.impl.service;

import com.exercise.impl.cache.CountryCache;
import com.exercise.impl.dto.Country;
import com.exercise.impl.dto.RequestParams;
import com.exercise.impl.model.entity.Customer;
import com.exercise.impl.repository.CustomerRepository;
import com.exercise.impl.specifications.PhoneNumberSpecifications;
import com.exercise.impl.validation.PhoneNumberValidator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CountryCache cache;

    public CustomerService(CustomerRepository customerRepository, CountryCache cache) {
        this.customerRepository = customerRepository;
        this.cache = cache;
    }

    public List<Country> getAllCountries() {
        List<Country> countries = new ArrayList<>();
        cache.getAllCountries().forEach((key, value) -> countries.add(value));
        return countries;
    }

    public List<Customer> getAllPhoneNumbers(RequestParams requestParams) {
        Specification<Customer> specifications = PhoneNumberSpecifications.filter(requestParams);
        List customers = customerRepository.findAll(specifications);
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator(requestParams, customers);
        return phoneNumberValidator.validate();
    }

}
