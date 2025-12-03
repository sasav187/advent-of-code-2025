package day03.part1;

import java.util.*;
import java.io.*;

public class Main {

    public static final String inputFile = "inputs" + File.separator + "day3.txt";
    public static List<String> batteries = new ArrayList<>();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

            String line;

            while ((line = br.readLine()) != null) {
                batteries.add(line);
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        int n = 0;

        for (String battery : batteries) {
            n += findJoltage(battery);
        }

        System.out.println(n);
    }

    private static int findJoltage(String battery) {

        int max = 0;

        for (int i = 0; i < battery.length(); i++) {

            int a = battery.charAt(i) - '0';

            for (int j = i + 1; j < battery.length(); j++) {

                int b = battery.charAt(j) - '0';

                int val = a * 10 + b;
                if (val > max) {
                    max = val;
                }
            }
        }

        return max;
    }
}
