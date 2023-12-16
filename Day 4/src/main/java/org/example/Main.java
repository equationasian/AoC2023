package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static final int NUM_CARDS = 196;
    public static final int NUM_WINNING_NUMBERS = 10;
    //public static final int NUM_ELF_NUMBERS = 25;

    public static void main(String[] args) {
        List<Integer> numCardCopies = initializeList();
        int sum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            //while (line != null) {
                String delimiters = "\s\\|\s";
                String[] lineSplit = line.split(delimiters);
                lineSplit[1] = lineSplit[1].trim();

                String[] winningNumbersSplit = lineSplit[0].split("\s+");
                int currentCardNumber = winningNumbersSplit[1].charAt(0);
                System.out.println(currentCardNumber);
                String[] elfNumbersSplit = lineSplit[1].split("\s+");

                List<Integer> winningNumbers = Arrays.stream(winningNumbersSplit)
                        .map(Integer::parseInt)
                        .toList();

                List<Integer> elfNumbers = Arrays.stream(elfNumbersSplit)
                        .map(Integer::parseInt)
                        .toList();

                sum += countWinningNumbers(winningNumbers, elfNumbers);

                //line = reader.readLine();
            //}
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(sum);
    }

    public static int countWinningNumbers(List<Integer> winningNumbers, List<Integer> elfNumbers) {
        int sum = 0;

        for (int i = 0; i < NUM_WINNING_NUMBERS; ++i) {
            if (elfNumbers.contains(winningNumbers.get(i))) {
                ++sum;
            }
        }

        return sum;
    }

    public static List<Integer> initializeList() {
        List<Integer> cardsList = new ArrayList<>();

        for (int i = 0; i < NUM_CARDS; ++i) {
            cardsList.add(1);
        }

        return cardsList;
    }
}