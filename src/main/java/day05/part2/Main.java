package day05.part2;

import java.util.*;
import java.io.*;

public class Main {

    private static final String INPUT_FILE = "inputs" + File.separator + "day5.txt";
    private static List<long[]> ranges = new ArrayList<>();

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("-")) {
                    String[] split = line.split("-");

                    long start = Long.parseLong(split[0]);
                    long end = Long.parseLong(split[1]);
                    ranges.add(new long[]{start, end});
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        ranges.sort(Comparator.comparingLong(o -> o[0]));

        List<long[]> merged = new ArrayList<>();
        long[] current = ranges.get(0);

        for (int i = 1; i < ranges.size(); i++) {
            long[] next = ranges.get(i);

            if (next[0] < current[1] + 1) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                merged.add(current);
                current = next;
            }
        }

        merged.add(current);

        long total = 0;
        for (long[] m : merged) {
            total += (m[1] - m[0] + 1);
        }

        System.out.println(total);
    }
}
