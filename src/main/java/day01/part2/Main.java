package day01.part2;

import java.io.*;
import java.util.*;

public class Main {

    public static final String inputFile = "input.txt";
    public static List<String> cypher = new ArrayList<>();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

            String line;

            while ((line = br.readLine()) != null) {
                cypher.add(line);
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        int n = decrypt(cypher);
        System.out.println(n);
    }


    public static int decrypt(List<String> cypher) {
        int n = 0;
        int pos = 50;

        for (String s : cypher) {
            int steps = Integer.parseInt(s.substring(1));

            for (int i = 0; i < steps; i++) {
                if (s.charAt(0) == 'L') {
                    pos = (pos - 1 + 100) % 100;
                } else if (s.charAt(0) == 'R') {
                    pos = (pos + 1) % 100;
                }

                if (pos == 0) {
                    n++;
                }
            }
        }

        return n;
    }
}
