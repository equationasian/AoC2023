package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {
    //public static final int NUM_RED_CUBES = 12;
    //public static final int NUM_GREEN_CUBES = 13;
    //public static final int NUM_BLUE_CUBES = 14;

    public static void main(String[] args) {
        // gameID -> red
        //           green
        //           blue
        Map<Integer, List<Integer>> cubeCountMap = new HashMap<>();
        //int gameIDSum = 0;
        int powerSum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String delimiters = "[\s:,;]+";
            String line = reader.readLine();
            while (line != null) {
                String[] lineSplit = line.split(delimiters);
                Integer gameID = Integer.parseInt(lineSplit[1]);

                cubeCountMap.put(gameID, new ArrayList<>(Arrays.asList(0, 0, 0)));
                List<Integer> cubeListFromMap = cubeCountMap.get(gameID);
                for (int i = 2; i < lineSplit.length; i += 2) {
                    int numCubes = Integer.parseInt(lineSplit[i]);
                    String cubeColor = lineSplit[i + 1];

                    if (cubeColor.equals("red") && numCubes > cubeListFromMap.get(0)) {
                        cubeListFromMap.set(0, numCubes);
                    } else if (cubeColor.equals("green") && numCubes > cubeListFromMap.get(1)) {
                        cubeListFromMap.set(1, numCubes);
                    } else if (cubeColor.equals("blue") && numCubes > cubeListFromMap.get(2)) {
                        cubeListFromMap.set(2, numCubes);
                    }
                }

                int numRedCubes = cubeCountMap.get(gameID).get(0);
                int numGreenCubes = cubeCountMap.get(gameID).get(1);
                int numBlueCubes = cubeCountMap.get(gameID).get(2);

                int powerOfCubes = numRedCubes * numGreenCubes * numBlueCubes;
                powerSum += powerOfCubes;

                //if (numRedCubes <= NUM_RED_CUBES && numGreenCubes <= NUM_GREEN_CUBES && numBlueCubes <= NUM_BLUE_CUBES) {
                //    gameIDSum += gameID;
                //}

                line = reader.readLine();
            }

            System.out.println(powerSum);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}