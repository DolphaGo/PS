import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long x = Long.parseLong(st.nextToken());
        long y = Long.parseLong(st.nextToken());
        long z = (long) (1.0 * (y*100) / x ); //확률
        long s = 1;
        long e = 1000000000L;
        while (s < e) {
            long m = (s + e) >> 1;
            long newZ = (long) (1.0 * ((y + m)*100) / (x + m));
            if (newZ < z + 1) s = m + 1;
            else e = m;
        }
        long check = (long) (1.0 * ((y + e)*100) / (x + e));
        if (check > z) System.out.println(e);
        else System.out.println(-1);
    }
}