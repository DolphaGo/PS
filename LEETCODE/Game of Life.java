class Solution {
    static int dy[] = { -1, -1, -1, 0, 1, 1, 1, 0 };
    static int dx[] = { -1, 0, 1, 1, 1, 0, -1, -1 };

    public void gameOfLife(int[][] board) {
        int h = board.length;
        int w = board[0].length;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int count = getLiveCellAround(board, y, x);
                setState(board, y, x, count);
            }
        }

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                board[y][x] = board[y][x] % 2;
            }
        }
    }

    static int getLiveCellAround(int[][] board, int y, int x) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (!isRange(board, ny, nx)) { continue; }

            if (board[ny][nx] == 1 || board[ny][nx] == 2) {
                count++;
            }
        }
        return count;
    }

    static boolean isRange(int[][] board, int y, int x) {
        return 0 <= y && y < board.length && 0 <= x && x < board[0].length;
    }

    static void setState(int[][] board, int y, int x, int count) {
        int state = board[y][x];
        if (state == 0 && count == 3) { board[y][x] = 3; }
        if (state == 1 && (count < 2 || count > 3)) { board[y][x] = 2; }
    }
}