class Solution {
    public void rotate(int[][] matrix) {
        int n=matrix.length;
        for(int i = 0; i < n/2; ++i){
            int k = n - i-1;
            for(int j = i; j < k; ++j){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = temp;
            }
        }
    }
}