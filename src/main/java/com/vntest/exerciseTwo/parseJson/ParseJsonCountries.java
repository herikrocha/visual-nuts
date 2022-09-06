package com.vntest.exerciseTwo.parseJson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vntest.exerciseTwo.type.Country;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ParseJsonCountries {

    protected static final String PATH_FILE = "src/main/resources/countries.json";

    public List<Country> parseEntity() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Country> countries;
        try {
            countries = objectMapper.readValue(jsonFile(), new TypeReference<>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return countries;
    }

    public File jsonFile() {
        return new File(PATH_FILE);
    }
}
