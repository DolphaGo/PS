class Solution {
    public boolean isValidSudoku(char[][] board) {
        return sero(board) && garo(board) && sqaure(board);
    }

    private boolean garo(char[][] board) {
        for (int y = 0; y < 9; y++) {
            final int[] check = new int[10];
            for (int x = 0; x < 9; x++) {
                if (board[y][x] != '.') {
                    final int val = board[y][x] - '0';
                    if (check[val]++ > 0) { return false; }
                }
            }
        }
        return true;
    }

    private boolean sero(char[][] board) {
        for (int x = 0; x < 9; x++) {
            final int[] check = new int[10];
            for (int y = 0; y < 9; y++) {
                if (board[y][x] != '.') {
                    final int val = board[y][x] - '0';
                    if (check[val]++ > 0) { return false; }
                }
            }
        }
        return true;
    }

    private boolean sqaure(char[][] board) {
        for (int yy = 0; yy < 3; yy += 3) {
            for (int xx = 0; xx < 9; xx += 3) {
                final int[] check = new int[10];
                for (int y = yy; y < yy + 3; y++) {
                    for (int x = xx; x < xx + 3; x++) {
                        if (board[y][x] != '.') {
                            final int val = board[y][x] - '0';
                            if (check[val]++ > 0) { return false; }
                        }
                    }
                }
            }
        }
        return true;
    }

}