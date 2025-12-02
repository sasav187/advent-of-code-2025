package day02.part2;

import java.util.*;
import java.io.*;

public class Main {

    public static final String inputFile = "inputs" + File.separator + "day2.txt";
    public static List<String> ranges = new ArrayList();

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

            String line = br.readLine();

            String[] parts = line.split(",");

            for (String part : parts) {
                ranges.add(part);
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

            String[] parts = range.split("-");

            long num1 = Long.parseLong(parts[0]);
            long num2 = Long.parseLong(parts[1]);

            for (long i = num1; i <= num2; i++) {
                if (!isValid(i)) {
                    n += i;
                } else {
                    continue;
                }
            }

        }

        return n;
    }

    private static boolean isValid(long number) {

        String num = Long.toString(number);

        if (num.length() % 2 == 0) {

            String first = num.substring(0, num.length() / 2);
            String second = num.substring(num.length() / 2);

            return !first.equals(second);
        }

        return true;
    }
}
