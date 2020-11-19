import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] dp=new int[11];
        dp[1]=1; dp[2]=2; dp[3]=4;
        for(int i=4;i<=10;i++){
            dp[i]=dp[i-1]+dp[i-2]+dp[i-3];
        }

        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for(int tc=1;tc<=T;tc++){
            int val=Integer.parseInt(br.readLine());
            sb.append(dp[val]).append("\n");
        }
        System.out.print(sb);
    }
}