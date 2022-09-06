package com.vntest.exerciseOne.service;

import static com.vntest.exerciseOne.enums.DivisibleName.*;

public class PrintNumbersService {

    protected static final Integer MAXIMUM_VALUE = 100;

    public void printNumbers() {
        for (Integer count = 1; count <= MAXIMUM_VALUE; count++) {
            if (isDivisible(count, 15)) {
                printText(STRING_DIVISIBLE_BY_FIFTEEN.getDescription());
            } else if (isDivisible(count, 5)) {
                printText(STRING_DIVISIBLE_BY_FIVE.getDescription());
            } else if (isDivisible(count, 3)) {
                printText(STRING_DIVISIBLE_BY_THREE.getDescription());
            } else {
                printText(count.toString());
            }
        }
    }

    public boolean isDivisible(Integer dividend, Integer divider) {
        return dividend % divider == 0;
    }

    public void printText(String textToPrint) {
        System.out.println(textToPrint);
    }
}
