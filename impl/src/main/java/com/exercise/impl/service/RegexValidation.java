package com.exercise.impl.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@RequiredArgsConstructor
public class RegexValidation {
    private String regex;
    private String text;

    public RegexValidation(String regex, String text) {
        this.text = text;
        this.regex = regex;
    }

    public boolean isValid() {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            return true;
        }
        return false;
    }
}
