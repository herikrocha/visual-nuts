package com.vntest.exerciseTwo.process;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vntest.exerciseTwo.service.CountryService;
import com.vntest.exerciseTwo.type.Country;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CountryProcess {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File fileToImport;
    private List<Country> countries;

    public CountryProcess(File fileToImport) {
        this.fileToImport = fileToImport;
    }

    public void countriesOperation() {
        parseCountries();
        printSearchResults();
    }

    private void printSearchResults() {
        CountryService countryService = new CountryService(countries);
        StringBuilder countriesSearchResults = new StringBuilder();

        countriesSearchResults.append("\n- Countries in the world: \n")
                .append(countryService.numberOfCountries().toString());
        countriesSearchResults.append("\n\n- German speaking country(ies) with most official languages: ")
                .append(countryService.germanSpeakingCountriesWithHighestOfficialLanguages());
        countriesSearchResults.append("\n\n- All official languages spoken in the listed countries: \n")
                .append(countryService.numberOfAllOfficialLanguagesSpokenInCountries().toString());
        countriesSearchResults.append("\n\n- Country(ies) with the highest number of official languages: ")
                .append(countryService.countriesWithHighestOfficialLanguages());
        countriesSearchResults.append("\n\n- Most common official language(s) in the listed countries: ")
                .append(countryService.mostCommonOfficialLanguages());

        System.out.println(countriesSearchResults);
    }

    private void parseCountries() {
        try {
            this.countries = objectMapper.readValue(fileToImport, new TypeReference<>(){});
        } catch (IOException e) {
            throw new RuntimeException("Error importing Countries JSON file", e) ;
        }
    }

}
