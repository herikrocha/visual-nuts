package com.vntest.exerciseTwo;

import com.vntest.exerciseTwo.process.CountryProcess;

import java.io.File;

public class ExerciseTwo {

    private static final File FILE = new File("src/main/resources/countries.json");

    public static void main(String[] args) {
        countriesOperation();
    }

    protected static void countriesOperation() {
        CountryProcess countryProcess = new CountryProcess(FILE);
        countryProcess.countriesOperation();
    }

}
