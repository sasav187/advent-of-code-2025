package day02.part2;

import java.util.*;
import java.io.*;

public class Main {

    private static final String INPUT_FILE = "inputs" + File.separator + "day2.txt";
    private static List<String> ranges = new ArrayList<>();

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {

            String line = br.readLine();
            if (line != null) {
                String[] parts = line.split(",");
                for (String part : parts) {
                    ranges.add(part.trim());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        long n = idFinder(ranges);

        System.out.println(n);
    }

    private static long idFinder(List<String> ranges) {
        long n = 0;

        for (String range : ranges) {
            if (range.isBlank()) continue;
            String[] parts = range.split("-");

            long start = Long.parseLong(parts[0].trim());
            long end = Long.parseLong(parts[1].trim());

            for (long i = start; i <= end; i++) {
                if (!isValid(i)) {
                    n += i;
                }
            }
        }
        return n;
    }

    private static boolean isValid(long number) {
        String s = Long.toString(number);
        int len = s.length();

        for (int sub = 1; sub <= len / 2; sub++) {
            if (len % sub != 0) {
                continue;
            }

            String piece = s.substring(0, sub);
            int repeats = len / sub;

            boolean ok = true;
            for (int k = 1; k < repeats; k++) {
                int start = k * sub;
                if (!s.substring(start, start + sub).equals(piece)) {
                    ok = false;
                    break;
                }
            }

            if (ok && repeats >= 2) {
                return false;
            }
        }
        return true;
    }
}
