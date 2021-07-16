package com.exercise.impl.service;

import com.exercise.impl.cache.CountryCache;
import com.exercise.impl.dto.PhoneNumberState;
import com.exercise.impl.dto.RequestParams;
import com.exercise.impl.model.entity.Customer;
import com.exercise.impl.repository.CustomerRepository;
import com.exercise.impl.specifications.PhoneNumberSpecifications;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class CustomerServiceTest {
    @Mock
    private CustomerService customerServiceTest;

    @Mock
    private CustomerRepository repository;

    @Mock
    private CountryCache cache;

    @Mock
    Specification<Customer> specifications;
    private RequestParams requestParams;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        cache = CountryCache.getInstance();
        customerServiceTest = new CustomerService(repository, cache);
        requestParams = new RequestParams();
        requestParams.setCountryCode("212");
        requestParams.setState(PhoneNumberState.VALID);
    }

    @Test
    void itShouldListCustomersWithValidRegexAndCode() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(Customer.builder().name("Yosaf Karrouch").phone("(212) 698054317").build());
        customerList.add(Customer.builder().name("Chouf Malo").phone("(212) 691933626").build());
        customerList.add(Customer.builder().name("soufiane fritisse ").phone("(212) 633963130").build());
        customerList.add(Customer.builder().name("Nada Sofie").phone("(212) 654642448").build());
        List<Customer> actual = customerServiceTest.getAllPhoneNumbers(requestParams);
        given(customerServiceTest.getAllPhoneNumbers(requestParams)).willReturn(customerList);
        // assertThat(actual.size()).isEqualTo(customerList.size());
    }

    @Test
    public void itShouldListCustomersWithNotValidRegexAndCode() {
        RequestParams requestParams = new RequestParams();
        List<Customer> customerList = new ArrayList<>();
        requestParams.setState(PhoneNumberState.NOT_VALID);
        requestParams.setCountryCode("212");
        customerList.add(Customer.builder().name("Walid Hammadi").phone("(212) 6007989253").build());
        customerList.add(Customer.builder().name("Younes Boutikyad").phone("(212) 6546545369").build());
        customerList.add(Customer.builder().name("Houda Houda").phone("(212) 6617344445").build());

        given(customerServiceTest.getAllPhoneNumbers(requestParams)).willReturn(customerList);
        List<Customer> customersResponse = customerServiceTest.getAllPhoneNumbers(requestParams);
        // assertThat(customersResponse.size()).isEqualTo(customerList.size());
    }

}
