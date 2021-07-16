package com.exercise.impl.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PhoneNumberState {
    VALID(1), NOT_VALID(0);
    private final Integer value;
}
