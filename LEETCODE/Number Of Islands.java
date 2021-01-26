class Solution {
    public int numIslands(char[][] grid) {
        int h = grid.length;
        int w = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[h][w];

        int[] dy = { -1, 1, 0, 0 };
        int[] dx = { 0, 0, -1, 1 };

        int count = 0;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (grid[y][x] == '1' && !visit[y][x]) {
                    visit[y][x] = true;
                    ++count;
                    q.add(new int[] { y, x });
                    while (!q.isEmpty()) {
                        int[] p = q.poll();
                        for (int i = 0; i < 4; i++) {
                            int ny = p[0] + dy[i];
                            int nx = p[1] + dx[i];
                            if (ny >= 0 && nx >= 0 && ny < h && nx < w && grid[ny][nx] == '1' && !visit[ny][nx]) {
                                visit[ny][nx] = true;
                                q.add(new int[] { ny, nx });
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}