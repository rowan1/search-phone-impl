package com.exercise.impl.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

public class RegexValidationTest {
    private RegexValidation regexValidationTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @ParameterizedTest
    @CsvSource({"\\(212\\)\\ ?[5-9]\\d{8}$,(212) 698054317,true", "\\(212\\)\\ ?[5-9]\\d{8}$,(212) 6007989253,false"})
    void validateText(String regex, String text, boolean expected) {
        regexValidationTest = new RegexValidation(regex, text);
        boolean actual = regexValidationTest.isValid();
        assertThat(actual).isEqualTo(expected);
    }
}