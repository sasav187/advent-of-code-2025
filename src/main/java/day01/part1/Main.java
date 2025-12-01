package day01.part1;

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
        int start = 50;

        for (String s : cypher) {
            int number = Integer.parseInt(s.substring(1));

            if (s.charAt(0) == 'L') {
                start = (start - number + 100) % 100;
            } else if (s.charAt(0) == 'R') {
                start = (start + number) % 100;
            }

            if (start == 0) {
                n++;
            }
        }

        return n;
    }
}
