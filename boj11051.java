import java.io.*;
import java.util.*;
public class Main {
    static final int MOD=10007;
    static int dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
        dp=new int[n+1][k+1];
        System.out.println(nCr(n,k)%MOD);
    }
    static int nCr(int n,int k){
        if(dp[n][k]!=0) return dp[n][k];
        if(n==0 || k==0 || n==k) return dp[n][k]=1;
        else {
            dp[n-1][k]=nCr(n-1,k);
            dp[n-1][k-1]=nCr(n-1,k-1);
            return (dp[n-1][k]+dp[n-1][k-1])%MOD;
        }
    }
}