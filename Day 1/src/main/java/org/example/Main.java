package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int total = 0;
            StringBuilder twoDigitNumber = new StringBuilder();
            String line = reader.readLine();
            Pattern pattern = Pattern.compile("[1-9]|one|two|three|four|five|six|seven|eight|nine");
            Matcher matcher;

            while (line != null) {
                matcher = pattern.matcher(line);
                boolean found = matcher.find();
                String firstDigit = "";
                if (found) {
                    firstDigit = matcher.group();
                }

                String lastDigit = "";
                while (found) {
                    lastDigit = matcher.group();
                    found = matcher.find(matcher.start() + 1);
                }

                if (lastDigit.isEmpty()) {
                    lastDigit = firstDigit;
                }

                twoDigitNumber.append(strToInt(firstDigit)).append(strToInt(lastDigit));
                total += Integer.parseInt(twoDigitNumber.toString());
                System.out.println(twoDigitNumber.toString());
                twoDigitNumber.setLength(0);

                line = reader.readLine();
            }

            System.out.println(total);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int strToInt(String number) {
        return switch (number) {
            case "one" -> 1;
            case "two" -> 2;
            case "three" -> 3;
            case "four" -> 4;
            case "five" -> 5;
            case "six" -> 6;
            case "seven" -> 7;
            case "eight" -> 8;
            case "nine" -> 9;
            default -> Integer.parseInt(number);
        };
    }
}