package com.exercise.impl.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Country implements Serializable {
    private String name;
    private String code;
    private String regex;

    public Country(String name, String code, String regex) {
        this.name = name;
        this.code = code;
        this.regex = regex;
    }
}
