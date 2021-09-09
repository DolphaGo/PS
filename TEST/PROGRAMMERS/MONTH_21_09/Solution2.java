import java.util.*;

class Solution2 {
    static int[] dy = { -1, 0, 1, 0 };
    static int[] dx = { 0, 1, 0, -1 };

    public int[] solution(String[] grid) {
        int h = grid.length;
        int w = grid[0].length();

        char[][] map = new char[h][w];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                map[y][x] = grid[y].charAt(x);
            }
        }

        boolean[][][] check = new boolean[h][w][4];
        Queue<int[]> q = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        for (int sy = 0; sy < h; sy++) {
            for (int sx = 0; sx < w; sx++) {
                for (int i = 0; i < 4; i++) {

                    if (check[sy][sx][i]) { continue; }

                    q.add(new int[] { sy, sx, i, 0 });

                    while (!q.isEmpty()) {
                        int[] p = q.poll();
                        int cy = p[0];
                        int cx = p[1];
                        int dir = p[2]; // cy, cx로 이동하기 위해 움직인 방향
                        int len = p[3]; // 지금까지 이동한 거리

                        // 원점을 제외하고, 이미 요 방향으로 와본 사람들은 여기 다시 안올거임
                        if (check[cy][cx][dir]) {
                            // 현재 위치가 원점인데, 이동한 거리가 0이 아니면서, 이미 와봤던 방향이면 결과로 추가(이전에 이미 이 방향으로 와봤었다면 그게 곧 사이클이니까)
                            if (cy == sy && cx == sx && len != 0) {
                                result.add(len);
                            }
                            continue;
                        }

                        check[cy][cx][dir] = true; // 한번 온 방향은 다시 안갈거임

                        char c = map[cy][cx]; // 현재 위치를 가져온다.

                        // 현재 위치를 기준으로 어떻게 이동할 지
                        int ndir = dir;
                        if (c == 'R') { ndir += 1; }
                        if (c == 'L') { ndir -= 1; }

                        // 다음 방향을 구했음
                        ndir = (ndir + 4) % 4;

                        // 이동하자
                        int ny = cy + dy[ndir];
                        int nx = cx + dx[ndir];

                        // 바운더리 처리
                        ny = (ny + h) % h;
                        nx = (nx + w) % w;
                        q.add(new int[] { ny, nx, ndir, len + 1 });
                    }

                }
            }
        }

        Collections.sort(result);
        int[] answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}
