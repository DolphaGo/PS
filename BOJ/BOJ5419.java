import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int[] tree;
    static int[][] arr;
    static Integer[] ys;

    static long query(int node, int start, int end, int left, int right) {
        if (right < start || left > end) { return 0; }
        if (left <= start && end <= right) {
            return tree[node];
        }
        int m = (start + end) >> 1;
        return query(node << 1, start, m, left, right) + query(node << 1 | 1, m + 1, end, left, right);
    }

    static int update(int node, int start, int end, int idx) {
        if (idx < start || idx > end) { return tree[node]; }
        if (start == end) {
            return tree[node] += 1;
        }
        int m = (start + end) >> 1;
        return tree[node] = update(node << 1, start, m, idx) + update(node << 1 | 1, m + 1, end, idx);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());
            arr = new int[n][2];
            ys = new Integer[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
                ys[i] = arr[i][1];
            }

            // y 좌표는 내림차순으로 정렬
            Arrays.sort(ys, Collections.reverseOrder());
            int size = 0;
            int prev = Integer.MIN_VALUE;
            for (int y : ys) {
                if (prev == y) { continue; }
                map.put(y, ++size);
                prev = y;
            }

            Arrays.sort(arr, (o1, o2) -> {
                if (o1[0] == o2[0]) { return Integer.compare(o2[1], o1[1]); }
                return Integer.compare(o1[0], o2[0]);
            }); // x좌표 순으로 정렬(쿼리용), x좌표가 같으면 북쪽에 있는 섬먼저.

            int h = (int) Math.ceil(Math.log(size) / Math.log(2));
            tree = new int[1 << h + 1];
            long answer = 0;
            for (int i = 0; i < n; i++) {
                int val = map.get(arr[i][1]);
                answer += query(1, 1, size, 1, val);
                update(1, 1, size, val);
            }
            sb.append(answer).append('\n');
            map.clear();
        }
        System.out.print(sb);
    }
}
