package day09.part1;

import java.io.*;
import java.util.*;

public class Main {

    private static final String INPUT_FILE = "inputs" + File.separator + "day9.txt";

    record Point(int x, int y) {}

    public static void main(String[] args) {

        List<Point> pts = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[0]);
                pts.add(new Point(x, y));

            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        long maxArea = 0;

        for (int i = 0; i < pts.size(); i++) {
            for (int j = i + 1; j < pts.size(); j++) {
                Point p1 = pts.get(i);
                Point p2 = pts.get(j);

                long width = Math.abs(p2.x - p1.x) + 1;
                long height = Math.abs(p2.y - p1.y) + 1;
                long area = width * height;

                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }

        System.out.println(maxArea);
    }
}
