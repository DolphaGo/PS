import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        long[][] arr = new long[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            arr[i][0] = Math.min(s, e);
            arr[i][1] = Math.max(s, e);
        }

        long l = Long.parseLong(br.readLine());
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[1] == o2[1]) { return Long.compare(o1[0], o2[0]); }
            return Long.compare(o1[1], o2[1]);
        });

        PriorityQueue<Long> q = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            long s = arr[i][0];
            long e = arr[i][1];
            if (e - s > l) { continue; }
            q.add(s);
            while (!q.isEmpty() && q.peek() < e - l) {
                q.poll();
            }
            max = Math.max(max, q.size());
        }
        System.out.println(max);
    }
}