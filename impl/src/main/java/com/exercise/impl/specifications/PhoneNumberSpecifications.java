package com.exercise.impl.specifications;

import com.exercise.impl.dto.NullCheck;
import com.exercise.impl.dto.RequestParams;
import com.exercise.impl.model.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

public class PhoneNumberSpecifications {
    public static Specification<Customer> withCountries(String countryCode) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (new NullCheck(countryCode).isNull()) {
                return criteriaBuilder.conjunction();
            } else {
                return criteriaBuilder.like(root.get("phone"), "(" + countryCode + "%");
            }
        };
    }

    public static Specification<Customer> filter(RequestParams phoneNumberParams) {
        return Specification.where(withCountries(phoneNumberParams.getCountryCode()));
    }
}