import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] tree;
    static List<Point> list = new ArrayList<>();

    static long query(int left, int right) {
        long sum = 0;
        while (left < right) {
            if (left / 2 != (left + 1) / 2) {
                sum += tree[left];
            }
            if (right / 2 != (right - 1) / 2) {
                sum += tree[right];
            }
            left = (left + 1) / 2;
            right = (right - 1) / 2;
        }
        if (left == right) { sum += tree[left]; }
        return sum;
    }

    static void update(int idx) {
        while (idx > 0) {
            tree[idx] += 1;
            idx /= 2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list.add(new Point(x, y));
            }
            int[] arr=new int[4];
            list.sort((o1, o2) -> Integer.compare(o2.y, o1.y)); // 빠른 놈
            int size = 0;
            int prev = Integer.MIN_VALUE;
            for (Point data : list) {
                if (prev == data.y) { continue; }
                map.put(data.y, ++size);
                prev = data.y;
            }
            // y 좌표는 내림차순으로 정렬
            list.sort((o1, o2) -> {
                if (o1.x == o2.x) { return Integer.compare(o2.y, o1.y); }
                return Integer.compare(o1.x, o2.x);
            });

            int h = 1;
            while (h < size) {
                h *= 2;
            }
            tree = new int[h << 1];

            long answer = 0;
            for (int i = 0; i < n; i++) {
                long query = query(h, h + map.get(list.get(i).y) -1 );
                answer += query;
                update(h + map.get(list.get(i).y) - 1);
            }
            sb.append(answer).append('\n');
            list.clear();
            map.clear();
        }
        System.out.print(sb);
    }
}
