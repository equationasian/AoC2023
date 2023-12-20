package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        List<String> puzzleInput = getPuzzleInputGrid();
        Pattern pattern = Pattern.compile("\\*");
        Matcher matcher;
        int sum = 0;

        for (int row = 0; row < puzzleInput.size(); ++row) {
            String currentRow = puzzleInput.get(row);
            matcher = pattern.matcher(currentRow);

            while (matcher.find()) {
                List<Integer> numberList = new ArrayList<>();
                int startIndex = matcher.start();
                String checkRow;
                char checkDigit;

                // check up, northeast, northwest
                if (row - 1 >= 0) {
                    checkRow = puzzleInput.get(row - 1);

                    // up
                    checkDigit = checkRow.charAt(startIndex);
                    if (isNumber(checkDigit)) {
                        numberList.add(getFullNumber(checkRow, startIndex));
                    }
                    else {
                        // northeast
                        if (startIndex + 1 < checkRow.length()) {
                            checkDigit = checkRow.charAt(startIndex + 1);
                            if (isNumber(checkDigit)) {
                                numberList.add(getFullNumber(checkRow, startIndex + 1));
                            }
                        }

                        // northwest
                        if (startIndex - 1 >= 0) {
                            checkDigit = checkRow.charAt(startIndex - 1);
                            if (isNumber(checkDigit)) {
                                numberList.add(getFullNumber(checkRow, startIndex - 1));
                            }
                        }
                    }
                }

                // check down, southwest, southeast
                if (row + 1 < puzzleInput.size()) {
                    checkRow = puzzleInput.get(row + 1);

                    // down
                    checkDigit = checkRow.charAt(startIndex);
                    if (isNumber(checkDigit)) {
                        numberList.add(getFullNumber(checkRow, startIndex));
                    }
                    else {
                        // southwest
                        if (startIndex - 1 > 0) {
                            checkDigit = checkRow.charAt(startIndex - 1);
                            if (isNumber(checkDigit)) {
                                numberList.add(getFullNumber(checkRow, startIndex - 1));
                            }
                        }

                        // southeast
                        if (startIndex + 1 < checkRow.length()) {
                            checkDigit = checkRow.charAt(startIndex + 1);
                            if (isNumber(checkDigit)) {
                                numberList.add(getFullNumber(checkRow, startIndex + 1));
                            }
                        }
                    }
                }

                // left
                if (startIndex - 1 >= 0) {
                    char leftChar = currentRow.charAt(startIndex - 1);
                    if (isNumber(leftChar)) {
                        numberList.add(getFullNumber(currentRow, startIndex - 1));
                    }
                }

                // right
                if (startIndex + 1 < currentRow.length()) {
                    char rightChar = currentRow.charAt(startIndex + 1);
                    if (isNumber(rightChar)) {
                        numberList.add(getFullNumber(currentRow, startIndex + 1));
                    }
                }

                if (numberList.size() == 2) {
                    sum += numberList.get(0) * numberList.get(1);
                }
            }
        }

        System.out.println(sum);
    }

    public static int getFullNumber(String row, int numberIndex) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(row);

        while (matcher.find()) {
            if (numberIndex >= matcher.start() && numberIndex <= (matcher.end() - 1)) {
                return Integer.parseInt(matcher.group());
            }
        }

        return 0;
    }

    public static boolean isNumber(Character digitToCheck) {
        return (Character.isDigit(digitToCheck));
    }

    public static boolean isSymbol(Character digitToCheck) {
        return (!Character.isDigit(digitToCheck) && digitToCheck != '.');
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
            logger.info(e.getMessage());
        }

        return puzzleInput;
    }
}