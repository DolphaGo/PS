import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int[][] dp=new int[n+1][3];
        int max=0;
        for(int i=1;i<n+1;i++){
            int val=Integer.parseInt(br.readLine());
            dp[i][0]=max;
            dp[i][1]=dp[i-1][0]+val;
            dp[i][2]=dp[i-1][1]+val;
            max=Math.max(max,Math.max(dp[i][1],dp[i][2]));
        }
        System.out.println(max);
    }
}