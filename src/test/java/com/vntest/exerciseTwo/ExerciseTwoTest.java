package com.vntest.exerciseTwo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ExerciseTwoTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void countriesOperationTest() {
        ExerciseTwo.countriesOperation();
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

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
