package com.vntest.exerciseTwo;

import com.vntest.exerciseTwo.parseJson.ParseJsonCountries;
import com.vntest.exerciseTwo.service.CountryService;
import com.vntest.exerciseTwo.type.Country;

import java.util.List;

public class ExerciseTwo {

    public static void main(String[] args) {
        countriesOperation();
    }
    
    protected static void countriesOperation() {
        List<Country> countries = new ParseJsonCountries().parseEntity();
        CountryService CountryService = new CountryService(countries);
        StringBuilder countriesSearchResults = new StringBuilder();

        countriesSearchResults.append("\n- Countries in the world: \n")
                .append(CountryService.numberOfCountries().toString());
        countriesSearchResults.append("\n\n- German speaking country(ies) with most official languages: ")
                .append(CountryService.germanSpeakingCountriesWithHighestOfficialLanguages());
        countriesSearchResults.append("\n\n- All official languages spoken in the listed countries: \n")
                .append(CountryService.numberOfAllOfficialLanguagesSpokenInCountries().toString());
        countriesSearchResults.append("\n\n- Country(ies) with the highest number of official languages: ")
                .append(CountryService.countriesWithHighestOfficialLanguages());
        countriesSearchResults.append("\n\n- Most common official language(s) in the listed countries: ")
                .append(CountryService.mostCommonOfficialLanguages());

        printValue(countriesSearchResults);
    }

    private static void printValue(StringBuilder stringToPrint) {
        System.out.println(stringToPrint);
    }

}
