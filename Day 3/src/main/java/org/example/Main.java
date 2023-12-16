package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        List<String> puzzleInput = getPuzzleInputGrid();
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher;
        int sum = 0;

        for (int row = 0; row < puzzleInput.size(); ++row) {
            String currentRow = puzzleInput.get(row);
            matcher = pattern.matcher(currentRow);
            while (matcher.find()) {
                boolean adjacent = isAdjacentToSymbol(puzzleInput, matcher);
            }
        }
    }

    public static boolean isAdjacentToSymbol(List<String> puzzleInput, Matcher matcher) {
        // System.out.println(matcher.group());
        String number = matcher.group();
        int startIndex = matcher.start();
        int lastIndex = matcher.end();

        System.out.println(startIndex);

        // up
        int check = startIndex - 1;
        if (check >= 0) {
            //String aboveNumber = puzzleInput.get();
        }

        return false;
    }

    public static List<String> getPuzzleInputGrid() {
        List<String> puzzleInput = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                puzzleInput.add(line);
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return puzzleInput;
    }
}