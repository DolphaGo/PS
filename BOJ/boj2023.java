import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        go(n, 0,0);
        System.out.print(sb);
    }

    static void go(int v, int cur, int res) {
        if (v == 0) {
            if (String.valueOf(res).length() == n) {
                sb.append(res).append('\n');
            }
            return;
        }
        for (int i = 1; i <= 9; i++) {
            int ncur = cur * 10 + i;
            if (isPrime(ncur)) {
                int nres = res + i * (int) Math.pow(10, v - 1);
                go(v - 1, ncur, nres);
            }
        }

    }

    static boolean isPrime(int val) {
        if (val < 2) { return false; }
        for (int i = 2; i * i <= val; i++) {
            if (val % i == 0) { return false; }
        }
        return true;
    }
}
