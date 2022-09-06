package com.vntest.exerciseOne;

import com.vntest.exerciseOne.service.PrintNumbersService;

public class ExerciseOne {

    public static void main(String[] args) {
        printNumbersOperation();
    }

    private static void printNumbersOperation() {
        PrintNumbersService printNumbersService = new PrintNumbersService();
        printNumbersService.printNumbers();
    }
}