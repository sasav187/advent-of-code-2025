package day06.part2;

import java.io.*;
import java.util.*;

public class Main {

    private static final String INPUT_FILE = "inputs" + File.separator + "day6.txt";

    public static void main(String[] args) throws IOException {

        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        int rows = lines.size();
        int cols = lines.get(0).length();

        long grandTotal = 0;

        int c = cols - 1;
        while (c >= 0) {

            if (isSpaceColumn(lines, c)) {
                c--;
                continue;
            }

            List<Long> numbers = new ArrayList<>();
            int startCol = c;

            while (c >= 0 && !isSpaceColumn(lines, c)) {
                StringBuilder sb = new StringBuilder();
                for (int r = 0; r < rows - 1; r++) {
                    char ch = lines.get(r).charAt(c);
                    if (Character.isDigit(ch)) sb.append(ch);
                }
                if (sb.length() > 0) numbers.add(Long.parseLong(sb.toString()));
                c--;
            }

            Collections.reverse(numbers);

            char op = lines.get(rows - 1).charAt(c + 1);

            long result = (op == '+') ? 0 : 1;
            for (long n : numbers) {
                if (op == '+') result += n;
                else result *= n;
            }

            grandTotal += result;
        }

        System.out.println(grandTotal);
    }

    private static boolean isSpaceColumn(List<String> lines, int col) {
        for (String line : lines) {
            if (col < line.length() && !Character.isWhitespace(line.charAt(col))) return false;
        }
        return true;
    }
}