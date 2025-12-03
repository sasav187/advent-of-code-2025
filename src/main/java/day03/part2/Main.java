package day03.part2;

import java.util.*;
import java.io.*;

public class Main {

    private static final String INPUT_FILE = "inputs" + File.separator + "day3.txt";
    private static List<String> batteries = new ArrayList<>();
    private static final int K = 12;

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {
                batteries.add(line);
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        long n = 0;
        for (String battery : batteries) {
            n += findJoltage(battery);
        }
        System.out.println(n);
    }

    private static long findJoltage(String battery) {
        int n = battery.length();
        int remove = n - K;
        char[] stack = new char[K];
        int top = 0;

        for (int i = 0; i < n; i++) {

            char c = battery.charAt(i);

            while (top > 0 && remove > 0 && stack[top - 1] < c) {
                top--;
                remove--;
            }

            if (top < K) {
                stack[top++] = c;
            } else {
                remove = Math.max(0, remove - 1);
            }
        }

        int finalLen = top - remove;
        if (finalLen < 0) finalLen = 0;
        if (finalLen > K) finalLen = K;

        StringBuilder sb = new StringBuilder(K);

        for (int i = 0; i < finalLen; i++) {
            sb.append(stack[i]);
        }

        if (sb.length() > K) {
            sb.setLength(K);
        }

        return Long.parseLong(sb.toString());
    }
}
