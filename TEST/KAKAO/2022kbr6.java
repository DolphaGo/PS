class Solution {
    //    public int[] solution(int n, int[] info) {
//        int[] answer = {};
//        return answer;
//}

    public int solution(int[][] board, int[][] skill) {
        final int h = board.length;
        final int w = board[0].length;
        int length = h * w;
        int[] tree = new int[length];
        int[] lazy = new int[length];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                tree[y * board[0].length + x] = board[y][x];
            }
        }

        for (int[] sk : skill) {
            final int type = sk[0];
            final int r1 = sk[1];
            final int c1 = sk[2];
            final int r2 = sk[3];
            final int c2 = sk[4];
            final int degree = sk[5];
            final int diff = type == 1 ? -1 * degree : degree;
            update(tree, lazy, 1, 1, r2, c2, diff);
            update(tree, lazy, 1, 1, r2, c2, diff);
            update(tree, lazy, 1, 1, r2, c2, diff);
            update(tree, lazy, 1, 1, r2, c2, diff);
        }

        return getResult(board);
    }

    private void lazyUpdate(int node, int start, int end) {
        if (lazy[node]) {
            tree[node] = (end - start + 1) - tree[node];
            if (start != end) {
                lazy[node * 2] = !lazy[node * 2];
                lazy[node * 2 + 1] = !lazy[node * 2 + 1];
            }
            lazy[node] = false;
        }
    }

    private void update(int node, int start, int end, int left, int right) {
        lazyUpdate(node, start, end);
        if (start > right || end < left) {return;}

        if (left <= start && end <= right) {
            tree[node] = (end - start + 1) - tree[node];
            if (start != end) {
                lazy[node * 2] = !lazy[node * 2];
                lazy[node * 2 + 1] = !lazy[node * 2 + 1];
            }
            return;
        }

        int mid = (start + end) >> 1;
        update(node * 2, start, mid, left, right);
        update(node * 2 + 1, mid + 1, end, left, right);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private int getResult(final int[][] board) {
        int res = 0;
        return res;
    }

    // (r2, c2)까지 업데이트, (r1-1, c2) 빼고, (r2, c1-1) 빼고, (r1-1, c1-1) 은 두번 빼졌으니까 더해주면 됨
    // 2차원 세그나 펜윅쓰면 될 것 같은데, 귀찮다
    private void update(final int[][] board, final int r1, final int c1, final int r2, final int c2, final int value) {
        int r = r2;
        while (r > r1) {
            int c = c2;
            while (c > c1) {
                board[r][c] += value;
                c -= (c & -c);
            }
            r -= (r & -r);
        }
    }
}
