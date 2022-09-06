package com.vntest.exerciseOne.service;

import com.vntest.exerciseOne.service.PrintNumbersService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.vntest.exerciseOne.enums.DivisibleName.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PrintNumbersServiceTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void printNumbersTest() {
        printNumbersService.printNumbers();
        String lines[] = outputStreamCaptor.toString().split("\\r?\\n");
        for (int i = 0; i < lines.length; i++) {
            if (printNumbersService.isDivisible(i+1, 15)) {
                assertEquals(lines[i], STRING_DIVISIBLE_BY_FIFTEEN.getDescription());
            } else if (printNumbersService.isDivisible(i+1, 3)) {
                assertEquals(lines[i], STRING_DIVISIBLE_BY_THREE.getDescription());
            } else if (printNumbersService.isDivisible(i+1, 5)) {
                assertEquals(lines[i], STRING_DIVISIBLE_BY_FIVE.getDescription());
            } else {
                assertEquals(lines[i], lines[i].toString());
            }
        }
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @InjectMocks
    PrintNumbersService printNumbersService;
}
