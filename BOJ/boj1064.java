import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] A = { toInt(st.nextToken()), toInt(st.nextToken()) };
        int[] B = { toInt(st.nextToken()), toInt(st.nextToken()) };
        int[] C = { toInt(st.nextToken()), toInt(st.nextToken()) };

        double ab = getDistance(A, B);
        double bc = getDistance(B, C);
        double ca = getDistance(C, A);

        double answer = 0;
        if (getSlope(A, B) == getSlope(B, C)) { answer = -1; } else {
            double h1 = 2 * ab + 2 * bc;
            double h2 = 2 * bc + 2 * ca;
            double h3 = 2 * ca + 2 * ab;

            double min = Math.min(Math.min(h1, h2), h3);
            double max = Math.max(Math.max(h1, h2), h3);
            answer = max - min;
        }

        System.out.println(answer);
    }

    static int toInt(String s) {
        return Integer.parseInt(s);
    }

    static double getDistance(int[] a, int[] b) {
        return Math.sqrt(Math.abs(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2)));
    }

    static double getSlope(int[] a, int[] b) {
        if (a[1] == b[1]) { return 0; }
        return 1.0 * (a[0] - b[0]) / (a[1] - b[1]);
    }
}
