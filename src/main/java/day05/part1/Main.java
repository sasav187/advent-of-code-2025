package day05.part1;

import java.util.*;
import java.io.*;

public class Main {

    private static final String INPUT_FILE = "inputs" + File.separator + "day5.txt";
    private static List<String> ranges = new ArrayList<>();
    private static List<Long> ids = new ArrayList<>();

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("-")) {
                    ranges.add(line);
                } else if (!line.equals("")) {
                    ids.add(Long.parseLong(line));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Long> fresh = new ArrayList<>();

        for (String range : ranges) {

            String[] split = range.split("-");

            long num1 = Long.parseLong(split[0]);
            long num2 = Long.parseLong(split[1]);

            for (Long id : ids) {
                if (id >= num1 && id <= num2 && !fresh.contains(id)) {
                    fresh.add(id);
                }
            }
        }

        System.out.println(fresh.size());
    }
}
