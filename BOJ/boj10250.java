import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        final int T = Integer.parseInt(br.readLine());
        final StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            final int h = Integer.parseInt(st.nextToken());
            final int w = Integer.parseInt(st.nextToken());
            final int n = Integer.parseInt(st.nextToken());

            int yy = n % h == 0 ? h : n % h;
            int xx = n % h == 0 ? n / h : n / h + 1;

            sb.append(yy + String.format("%02d", xx)).append("\n");
        }
        System.out.print(sb);
    }
}
