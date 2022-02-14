import java.util.*;
import java.io.*;

public class Main {
    final static int[] dy = { -1, 1, 0, 0 };
    final static int[] dx = { 0, 0, -1, 1 };
    static int h, w;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        map = new char[h][w];
        for (int y = 0; y < h; y++) {
            map[y] = br.readLine().toCharArray();
        }

        int answer = 0;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                final char c = map[y][x];
                if (c == 'L') {
                    answer = Math.max(answer, queueing(y, x));
                }
            }
        }
        System.out.println(answer);
    }

    private static int queueing(final int y, final int x) {
        int max = 0;
        final Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[h][w];
        visit[y][x] = true;
        q.add(new int[] { y, x, 0 });
        while (!q.isEmpty()) {
            final int[] p = q.poll();
            max = Math.max(max, p[2]);
            for (int i = 0; i < 4; i++) {
                int ny = p[0] + dy[i];
                int nx = p[1] + dx[i];
                if (ny >= 0 && nx >= 0 && ny < h && nx < w && map[ny][nx] == 'L' && !visit[ny][nx]) {
                    visit[ny][nx] = true;
                    q.add(new int[] { ny, nx, p[2] + 1 });
                }
            }
        }
        return max;
    }
}
