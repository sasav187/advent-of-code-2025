package day08.part2;

import java.io.*;
import java.util.*;

public class Main {

    private static final String INPUT_FILE = "inputs" + File.separator + "day8.txt";

    record Point(long x, long y, long z) {}

    record Edge(long dist, int u, int v, long x1, long x2) implements Comparable<Edge> {
        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) {

        List<Point> points = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] p = line.split(",");

                long x = Long.parseLong(p[0]);
                long y = Long.parseLong(p[1]);
                long z = Long.parseLong(p[2]);

                points.add(new Point(x, y, z));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int n = points.size();
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Point a = points.get(i);
                Point b = points.get(j);
                long dist = distSq(a, b);
                edges.add(new Edge(dist, i, j, a.x, b.x));
            }
        }

        edges.sort(null);

        DSU dsu = new DSU(n);
        long lastXProduct = 0;

        for (Edge e : edges) {
            int rootU = dsu.find(e.u);
            int rootV = dsu.find(e.v);

            if (rootU != rootV) {
                dsu.union(e.u, e.v);

                if (dsu.components == 1) {
                    lastXProduct = e.x1 * e.x2;
                    break;
                }
            }
        }

        System.out.println(lastXProduct);
    }

    private static long distSq(Point a, Point b) {

        long dx = a.x - b.x;
        long dy = a.y - b.y;
        long dz = a.z - b.z;

        return dx * dx + dy * dy + dz * dz;
    }

    static class DSU {
        int[] parent;
        int[] size;
        int components;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            components = n;
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
                int temp = a; a = b; b = temp;
            }
            parent[b] = a;
            size[a] += size[b];
            components--;
        }
    }
}