package day08.part1;

import java.io.*;
import java.util.*;

public class Main {

    private static final String INPUT_FILE = "inputs" + File.separator + "day8.txt";

    record Point(long x, long y, long z) {}

    record Edge(long dist, int u, int v) implements Comparable<Edge> {
        @Override public int compareTo(Edge o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();

        try (var reader = new BufferedReader(new FileReader(INPUT_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] p = line.split(",");

                points.add(new Point(
                        Long.parseLong(p[0]),
                        Long.parseLong(p[1]),
                        Long.parseLong(p[2])
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int n = points.size();
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long d = distanceSquared(points.get(i), points.get(j));
                edges.add(new Edge(d, i, j));
            }
        }

        edges.sort(null);

        DSU dsu = new DSU(n);

        int toConnect = Math.min(1000, edges.size());

        for (int i = 0; i < toConnect; i++) {
            Edge e = edges.get(i);
            dsu.union(e.u, e.v);
        }

        int[] componentSize = new int[n];

        for (int i = 0; i < n; i++) {
            componentSize[dsu.find(i)]++;
        }

        List<Integer> sizes = new ArrayList<>();
        for (int sz : componentSize) {
            if (sz > 0) sizes.add(sz);
        }

        sizes.sort(Collections.reverseOrder());

        long answer = 1L;
        for (int i = 0; i < Math.min(3, sizes.size()); i++) {
            answer *= sizes.get(i);
        }

        System.out.println(answer);
    }

    private static long distanceSquared(Point a, Point b) {
        long dx = a.x - b.x;
        long dy = a.y - b.y;
        long dz = a.z - b.z;

        return dx * dx + dy * dy + dz * dz;
    }

    static class DSU {

        int[] parent;
        int[] size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        void union(int a, int b) {
            a = find(a);
            b = find(b);

            if (a == b) {
                return;
            }

            if (size[a] < size[b]) {
                int tmp = a;
                a = b;
                b = tmp;
            }

            parent[b] = a;
            size[a] += size[b];
        }
    }
}