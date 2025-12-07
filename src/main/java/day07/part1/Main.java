package day07.part1;

import java.util.*;
import java.io.*;

public class Main {

    private static final String INPUT_FILE = "inputs" + File.separator + "day7.txt";

    record Beam(int r, int c) {}

    public static void main(String[] args) {

        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        int rows = lines.size();
        int cols = lines.get(0).length();

        char[][] grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }


        int sr = -1;
        int sc = -1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'S') {
                    sr = i;
                    sc = j;
                }
            }
        }

        Queue<Beam> q = new ArrayDeque<>();
        q.add(new Beam(sr + 1, sc));

        boolean[][] visited = new boolean[rows][cols];
        int splits = 0;

        while (!q.isEmpty()) {

            Beam beam = q.poll();
            int r = beam.r;
            int c = beam.c;

            while (r < rows && c >= 0 && c < cols) {

                if (grid[r][c] == '^') {

                    if (!visited[r][c]) {

                        visited[r][c] = true;
                        splits++;

                        if (c - 1 >= 0) {
                            q.add(new Beam(r + 1, c - 1));
                        }

                        if (c + 1 < cols) {
                            q.add(new Beam(r + 1, c + 1));
                        }
                    }
                    break;
                }

                r++;
            }
        }

        System.out.println(splits);
    }
}
