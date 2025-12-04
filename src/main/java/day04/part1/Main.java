package day04.part1;

import java.util.*;
import java.io.*;

public class Main {

    private static String INPUT_FILE = "inputs" + File.separator + "day4.txt";
    private static char[][] diagram;

    public static void main(String[] args) {

        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        int rowCount = lines.size();
        int colCount = lines.get(0).length();
        diagram = new char[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            diagram[i] = lines.get(i).toCharArray();
        }

        int total = 0;

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                int neighbours;
                if (diagram[i][j] == '@') {
                    neighbours = getNeighbours(i, j);
                    if (neighbours < 4) {
                        total++;
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }

        System.out.println(total);
    }

    private static int getNeighbours(int row, int col) {
        int neigbours = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i < 0 || j < 0 || i >= diagram.length || j >= diagram[i].length || (i == row && j == col)) {
                    continue;
                }
                if (diagram[i][j] == '@') {
                    neigbours++;
                }
            }
        }

        return neigbours;
    }
}
