package com.exercise.impl.cache;

import com.exercise.impl.dto.Country;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CountryCache {

    private static HashMap<String, Country> countriesMap;
    private static CountryCache instance = null;

    public static CountryCache getInstance() {
        if (instance == null) {
            return new CountryCache();
        }
        return instance;
    }

    private CountryCache() {
        countriesMap = new HashMap();
        prepareCountryCache();
    }

    public void prepareCountryCache() {
        Country country = new Country("Cameroon", "237", "\\(237\\)\\ ?[2368]\\d{7,8}$");
        insertNewCountry("237", country);

        country = new Country("Ethiopia", "251", "\\(251\\)\\ ?[1-59]\\d{8}$");
        insertNewCountry("251", country);

        country = new Country("Morocco", "212", "\\(212\\)\\ ?[5-9]\\d{8}$");
        insertNewCountry("212", country);

        country = new Country("Mozambique", "258", "\\(258\\)\\ ?[28]\\d{7,8}$");
        insertNewCountry("258", country);

        country = new Country("Uganda", "256", "\\(256\\)\\ ?\\d{9}$");
        insertNewCountry("256", country);
    }

    public void insertNewCountry(String name, Country country) {
        countriesMap.put(name, country);
    }

    public HashMap<String, Country> getAllCountries() {
        return countriesMap;
    }
}
