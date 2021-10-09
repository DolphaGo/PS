package yogiyo;

import java.util.*;

class Solution {
    static int dy[] = { -1, 1, 0, 0 };
    static int dx[] = { 0, 0, -1, 1 };

    public int[] solution(String[] B) {
        int h = B.length;
        int w = B[0].length();
        boolean[][] map = new boolean[h][w];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                map[y][x] = B[y].charAt(x) == '#' ? true : false;
            }
        }

        boolean[][] visit = new boolean[h][w];
        Queue<int[]> q = new LinkedList<>();
        int[] answer = new int[3];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (map[y][x] && !visit[y][x]) {
                    visit[y][x] = true;
                    q.add(new int[] { y, x });
                    int length = 1;
                    while (!q.isEmpty()) {
                        int[] p = q.poll();
                        for (int i = 0; i < 4; i++) {
                            int ny = p[0] + dy[i];
                            int nx = p[1] + dx[i];
                            if (ny >= 0 && nx >= 0 && ny < h && nx < w && map[ny][nx] && !visit[ny][nx]) {
                                length++;
                                visit[ny][nx] = true;
                                q.add(new int[] { ny, nx });
                            }
                        }
                    }
                    answer[length - 1]++;
                }
            }
        }

        return answer;
    }
}
