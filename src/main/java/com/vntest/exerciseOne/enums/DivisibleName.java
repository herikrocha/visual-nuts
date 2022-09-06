package com.vntest.exerciseOne.enums;

import lombok.Getter;

@Getter
public enum DivisibleName {

    STRING_DIVISIBLE_BY_THREE("Visual"),
    STRING_DIVISIBLE_BY_FIVE("Nuts"),
    STRING_DIVISIBLE_BY_FIFTEEN("Visual Nuts");

    private String description;

    DivisibleName(String description) {
        this.description = description;
    }
}
