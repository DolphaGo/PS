class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp=new int[m][n];
        Arrays.fill(dp[0],1);
        for(int y=1;y<m;y++){
            dp[y][0]=1;
            for(int x=1;x<n;x++){
                dp[y][x]=dp[y-1][x]+dp[y][x-1];
            }
        }
        return dp[m-1][n-1];
    }
}