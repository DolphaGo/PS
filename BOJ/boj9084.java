import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[] coins = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int target = Integer.parseInt(br.readLine());
            int[] dp=new int[target+1];
            dp[0]=1;
            for(int coin:coins){
                for(int range=coin;range<=target;range++){
                    dp[range]+=dp[range-coin];
                }
            }
            sb.append(dp[target]).append('\n');
        }
        System.out.print(sb);
    }
}