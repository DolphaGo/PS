class Solution {
    public int numSquares(int n) {
        int[] dp=new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE/2);
        for(int i=1;i*i<=n;i++){
            dp[i*i]=1;
            for(int j=i*i+1;j<=n;j++){
                dp[j]=Math.min(dp[j],dp[j-i*i]+1);
            }
        }
        return dp[n];
    }
}