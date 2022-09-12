package com.vntest.exerciseTwo.service;

import com.vntest.exerciseTwo.type.Country;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CountryService {

    private List<Country> countries;

    public Integer numberOfCountries() {
        return countries.size();
    }

    public StringBuilder germanSpeakingCountriesWithHighestOfficialLanguages() {
        StringBuilder countriesText = new StringBuilder();
        List<Country> germanSpeakingCountries = countries
                .stream()
                .filter(country -> country.getLanguages().contains("de"))
                .collect(Collectors.toList());
        maxEntries(sizeOfLanguagesByCountry(germanSpeakingCountries))
                .forEach((country) -> countriesText.append("\n" + country));

        return countriesText;
    }

    public StringBuilder countriesWithHighestOfficialLanguages() {
        StringBuilder countriesText = new StringBuilder();
        maxEntries(sizeOfLanguagesByCountry(countries))
                .forEach((country) -> countriesText.append("\n" + country));

        return countriesText;
    }

    public Map<String, Integer> sizeOfLanguagesByCountry(List<Country> countriesList) {
        return countriesList
                .stream()
                .collect(Collectors.toMap(Country::getCountry, (country) -> country.getLanguages().size()));
    }

    public Integer numberOfAllOfficialLanguagesSpokenInCountries() {
        Set<String> numberOfSpokenLanguages = new HashSet<>();
        countries.forEach(country -> numberOfSpokenLanguages.addAll(country.getLanguages()));

        return numberOfSpokenLanguages.size();
    }

    public StringBuilder mostCommonOfficialLanguages() {
        StringBuilder mostPopularLanguagesText = new StringBuilder();

        maxEntries(getLanguagesByAmount())
                .forEach((language) -> mostPopularLanguagesText.append("\n" + language));

        return mostPopularLanguagesText;
    }

    public Map<String, Integer> getLanguagesByAmount() {
        List<String> languages = new ArrayList<>();
        Map<String, Integer> languagesByAmount = new HashMap<>();

        countries.forEach(country -> languages.addAll(country.getLanguages()));
        languages.forEach((language) -> {
            if (Objects.nonNull(languagesByAmount.get(language))) {
                languagesByAmount.put(language, languagesByAmount.get(language) + 1);
            } else {
                languagesByAmount.put(language, 1);
            }
        });
        return languagesByAmount;
    }

    public <K, V extends Comparable<V>> Set<K> maxEntries(Map<K, V> map) {
        Map.Entry<K, V> maxEntry = null;
        Set<K> maxEntries = new HashSet<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
                maxEntries.clear();
                maxEntries.add(entry.getKey());
            } else if (entry.getValue().compareTo(maxEntry.getValue()) == 0) {
                maxEntries.add(entry.getKey());
            }
        }

        return maxEntries;
    }
}
