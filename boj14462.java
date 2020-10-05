import java.io.*;
import java.util.*;

public class Main {
    static int[] left, right;
    static int[][] dp;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());

        left =new int[n];
        for(int i=0;i<n;i++) left[i]=Integer.parseInt(br.readLine());

        right =new int[n];
        for(int i=0;i<n;i++) right[i]=Integer.parseInt(br.readLine());

        dp=new int[n][n];
        for(int i=0;i<n;i++) Arrays.fill(dp[i],-1);
        System.out.println(go(0,0));
    }
    static int go(int l, int r){
        if(l==n || r==n) return 0;
        if(dp[l][r]!=-1) return dp[l][r];
        dp[l][r]=0;

        if(isOk(left[l],right[r])) dp[l][r]= go(l+1,r+1)+1;
        else dp[l][r]=Math.max(go(l,r+1), go(l+1,r));

        return dp[l][r];
    }
    static boolean isOk(int a,int b){
        return Math.abs(a-b)<=4;
    }
}