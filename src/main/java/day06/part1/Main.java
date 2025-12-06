package day06.part1;

import java.util.*;
import java.io.*;

public class Main {

    private static final String INPUT_FILE = "inputs" + File.separator + "day6.txt";

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

        int rows = lines.size();
        int numRows = rows - 1;

        List<List<Long>> numbersGrid = new ArrayList<>();
        for (int r = 0; r < numRows; r++) {
            List<Long> rowNumbers = new ArrayList<>();
            for (String s : lines.get(r).trim().split("\\s+")) {
                rowNumbers.add(Long.parseLong(s));
            }
            numbersGrid.add(rowNumbers);
        }

        List<Character> operators = new ArrayList<>();
        for (String s : lines.get(rows - 1).split("")) {
            if (s.equals("+") || s.equals("*")) {
                operators.add(s.charAt(0));
            }
        }

        long total = 0;
        int cols = operators.size();

        for (int col = 0; col < cols; col++) {
            long result;
            if (operators.get(col) == '+') {
                result = 0;
                for (int r = 0; r < numRows; r++) {
                    result += numbersGrid.get(r).get(col);
                }
            } else {
                result = 1;
                for (int r = 0; r < numRows; r++) {
                    result *= numbersGrid.get(r).get(col);
                }
            }
            total += result;
        }

        System.out.println(total);
    }
}
