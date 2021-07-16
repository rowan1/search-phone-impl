package com.exercise.impl.validation;

import com.exercise.impl.cache.CountryCache;
import com.exercise.impl.dto.Country;
import com.exercise.impl.dto.NullCheck;
import com.exercise.impl.dto.PhoneNumberState;
import com.exercise.impl.dto.RequestParams;
import com.exercise.impl.model.entity.Customer;
import com.exercise.impl.service.RegexValidation;

import java.util.*;

public class PhoneNumberValidator {
    RequestParams phoneNumberParams;
    NullCheck codeNullCheck;
    NullCheck stateNullChecker;
    List<Customer> customers;
    CountryCache cache;
    List<Customer> result;

    public PhoneNumberValidator(RequestParams phoneNumberParams, List<Customer> customers) {
        this.phoneNumberParams = phoneNumberParams;
        this.customers = customers;
        codeNullCheck = new NullCheck(phoneNumberParams.getCountryCode());
        stateNullChecker = new NullCheck(phoneNumberParams.getState());
        cache = CountryCache.getInstance();
        result = new ArrayList<>();
    }

    private boolean checkForValidCountryCode() {
        return codeNullCheck.isNotNull() && stateNullChecker.isNotNull();
    }

    public List<Customer> withCountryCodeAndRegex() {
        Country country = cache.getAllCountries().get(phoneNumberParams.getCountryCode());
        String regex = country.getRegex();
        regexValidate(regex);
        return result;
    }

    public void regexValidate(String regex) {
        List<Customer> temp = customers;
        Iterator<Customer> iterator = temp.iterator();
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            RegexValidation regexValidation = new RegexValidation(regex, customer.getPhone());
            addToResult(regexValidation.isValid(), customer);
            iterator.remove();
        }
    }

    public void addToResult(boolean isValid, Customer customer) {
        if (isValid && phoneNumberParams.getState().equals(PhoneNumberState.VALID)) result.add(customer);
        if (!isValid && phoneNumberParams.getState().equals(PhoneNumberState.NOT_VALID)) result.add(customer);
    }

    public List<Customer> validate() {
        if (checkForValidCountryCode()) {
            return withCountryCodeAndRegex();
        }

        return customers;
    }
}
