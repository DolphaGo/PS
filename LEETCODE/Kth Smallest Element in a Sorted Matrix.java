class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int h = matrix.length, w = matrix[0].length;
        int l = matrix[0][0], r = matrix[h - 1][w - 1];
        while (l < r) {
            int m = (l+r) / 2;
            int count = 0;
            int j = w - 1;
            for (int i = 0; i < h; i++) {
                while (j >= 0 && matrix[i][j] > m) j--;
                count += j + 1;
            }
            if (count < k) l = m + 1;
            else r = m;
        }
        return l;
    }
}