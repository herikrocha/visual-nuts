package com.vntest.exerciseTwo.process;

import com.vntest.exerciseTwo.type.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CountryProcessTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final File validFile = new File("src/test/resources/dummy-countries.json");
    private final File invalidFile = new File("src/test/resources/dummy-invalid-countries.json");

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(countryProcess, "fileToImport", validFile);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void checkCountriesOperation() {
        countryProcess.countriesOperation();
        Object countries = ReflectionTestUtils.getField(countryProcess, "countries"); //
        assertEquals(countries, getCountries());
        assertPrintResults();
    }

    @Test
    void checkExceptionAtParseJsonCountries() {
        ReflectionTestUtils.setField(countryProcess, "fileToImport", invalidFile);
        assertThrows(RuntimeException.class, () -> countryProcess.countriesOperation());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    private void assertPrintResults() {
        String lines[] = outputStreamCaptor.toString().split("\\r?\\n");

        for (int i = 0; i < lines.length; i++) {
            switch(lines[i]) {
                case "- Countries in the world: ":
                    assertEquals(lines[i+1], "5");
                    continue;
                case "- German speaking country(ies) with most official languages: ":
                case "- Country(ies) with the highest number of official languages: ":
                    assertEquals(lines[i+1], "BE");
                    continue;
                case "- All official languages spoken in the listed countries: ":
                    assertEquals(lines[i+1], "6");
                    continue;
                case "- Most common official language(s) in the listed countries: ":
                    assertEquals(lines[i+1], "de");
                    assertEquals(lines[i+2], "nl");
            }
        }
    }

    private List<Country> getCountries() {
        return Arrays.asList(
                Country.builder().country("US").languages(Arrays.asList("en")).build(),
                Country.builder().country("BE").languages(Arrays.asList("nl", "fr", "de")).build(),
                Country.builder().country("NL").languages(Arrays.asList("nl", "fy")).build(),
                Country.builder().country("DE").languages(Arrays.asList("de")).build(),
                Country.builder().country("ES").languages(Arrays.asList("es")).build()
        );
    }

    @InjectMocks
    CountryProcess countryProcess;
}
