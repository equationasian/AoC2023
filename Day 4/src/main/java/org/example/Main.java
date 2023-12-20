package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static final int NUM_CARDS = 196;
    public static final int NUM_WINNING_NUMBERS = 10;

    public static void main(String[] args) {
        List<Integer> numCardCopies = initializeList();
        int currentCardNumber = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                ++currentCardNumber;
                int copies = 0;

                String delimiters = "\s\\|\s";
                String[] lineSplit = line.split(delimiters);
                lineSplit[1] = lineSplit[1].trim();

                String[] winningNumbersSplit = lineSplit[0].split("\s+");
                winningNumbersSplit = Arrays.copyOfRange(winningNumbersSplit, 2, winningNumbersSplit.length);

                String[] elfNumbersSplit = lineSplit[1].split("\s+");

                List<Integer> winningNumbers = Arrays.stream(winningNumbersSplit)
                        .map(Integer::parseInt)
                        .toList();

                List<Integer> elfNumbers = Arrays.stream(elfNumbersSplit)
                        .map(Integer::parseInt)
                        .toList();

                copies = countWinningNumbers(winningNumbers, elfNumbers);

                for (int i = 0; i < numCardCopies.get(currentCardNumber - 1); ++i) {
                    incrementCardCopies(numCardCopies, currentCardNumber, copies);
                }

                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        int totalScratchcards = countScratchcards(numCardCopies);
        System.out.println(totalScratchcards);
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

    public static void incrementCardCopies(List<Integer> cardCopiesList, int current, int copies) {
        int totalCopies = current + copies;
        for (int i = current; i < totalCopies; ++i) {
            cardCopiesList.set(i, cardCopiesList.get(i) + 1);
        }
    }

    public static int countScratchcards(List<Integer> cardCopiesList) {
        return cardCopiesList.stream().mapToInt(Integer::intValue).sum();
    }
}