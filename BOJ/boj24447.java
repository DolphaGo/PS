import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int n = Integer.parseInt(st.nextToken());
        final int m = Integer.parseInt(st.nextToken());
        final int r = Integer.parseInt(st.nextToken());

        final PriorityQueue<Integer>[] pq = new PriorityQueue[n + 1];
        for (int i = 1; i <= n; i++) {
            pq[i] = new PriorityQueue<>();
        }

        boolean[] visit = new boolean[n + 1];
        long[] di = new long[n + 1];
        long[] ti = new long[n + 1];
        Arrays.fill(di, -1);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            final int u = Integer.parseInt(st.nextToken());
            final int v = Integer.parseInt(st.nextToken());
            pq[u].add(v);
            pq[v].add(u);
        }

        int count = 1;
        Queue<Integer> q = new LinkedList<>();
        visit[r] = true;
        ti[r] = count;
        di[r] = 0;
        q.add(r);

        while (!q.isEmpty()) {
            int p = q.poll();
            while (!pq[p].isEmpty()) {
                final Integer poll = pq[p].poll();
                if (!visit[poll]) {
                    visit[poll] = true;
                    di[poll] = di[p] + 1;
                    ti[poll] = ++count;
                    q.add(poll);
                }
            }
        }

        long answer = 0L;
        for (int i = 1; i <= n; i++) {
            answer += ti[i] * di[i];
        }

        System.out.println(answer);
    }
}
