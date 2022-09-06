package com.vntest.exerciseTwo.service;

import com.vntest.exerciseTwo.type.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    @Test
    void checkNumberOfCountries() {
        assertEquals(countryService.numberOfCountries(), 3);
    }

    @Test
    void checkGermanSpeakingCountriesWithHighestOfficialLanguages() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nPT");

        assertEquals(countryService.germanSpeakingCountriesWithHighestOfficialLanguages().compareTo(stringBuilder), 0);
    }

    @Test
    void checkNumberOfAllOfficialLanguagesSpokenInCountries() {
        assertEquals(countryService.numberOfAllOfficialLanguagesSpokenInCountries(), 5);
    }

    @Test
    void checkCountriesWithHighestOfficialLanguages() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nPT").append("\nFR");

        assertEquals(countryService.countriesWithHighestOfficialLanguages().compareTo(stringBuilder), 0);
    }

    @Test
    void checkMostCommonOfficialLanguages() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nde").append("\npt").append("\nen").append("\nfr").append("\nes");

        assertEquals(countryService.mostCommonOfficialLanguages().compareTo(stringBuilder), 0);
    }

    @BeforeEach
    public void setUp() {
        List<String> frLanguages = Arrays.asList("pt", "en", "fr", "es");
        List<String> geLanguages = Arrays.asList("de", "en");
        List<String> ptLanguages = Arrays.asList("pt", "fr", "de", "es");
        Country france = Country.builder().country("FR").languages(frLanguages).build();
        Country germany = Country.builder().country("GE").languages(geLanguages).build();
        Country portugal = Country.builder().country("PT").languages(ptLanguages).build();
        List<Country> countries = Arrays.asList(france, germany, portugal);
        ReflectionTestUtils.setField(countryService, "countries", countries);
    }

    @InjectMocks
    CountryService countryService;

}
