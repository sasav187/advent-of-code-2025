package day07.part2;

import java.io.*;
import java.util.*;

public class Main {

    private static final String INPUT_FILE = "inputs" + File.separator + "day7.txt";

    public static void main(String[] args) throws Exception {

        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            br.close();
        }

        int rows = lines.size();
        int cols = lines.get(0).length();

        char[][] grid = new char[rows][cols];

        for (int r = 0; r < rows; r++) {
            grid[r] = lines.get(r).toCharArray();
        }

        int sr = -1, sc = -1;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 'S') {
                    sr = r;
                    sc = c;
                }
            }
        }

        long[][] ways = new long[rows + 1][cols];

        if (sr + 1 <= rows - 1 && sc >= 0 && sc < cols) {
            ways[sr + 1][sc] = 1;
        }

        for (int r = sr + 1; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                long w = ways[r][c];
                if (w == 0) {
                    continue;
                }
                char cell = grid[r][c];

                if (cell == '^') {
                    if (c - 1 >= 0) {
                        ways[r + 1][c - 1] += w;
                    }
                    if (c + 1 < cols) {
                        ways[r + 1][c + 1] += w;
                    }
                } else {
                    ways[r + 1][c] += w;
                }
            }
        }


        long totalExits = 0;
        for (int c = 0; c < cols; c++) {
            totalExits += ways[rows][c];
        }

        for (int r = sr + 1; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                long w = ways[r][c];
                if (w == 0) {
                    continue;
                }
                if (grid[r][c] == '^') {
                    if (c - 1 < 0) {
                        totalExits += w;
                    }
                    if (c + 1 >= cols) {
                        totalExits += w;
                    }
                }
            }
        }

        System.out.println(totalExits);
    }
}
