import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] coins;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        for(int tc=1;tc<=3;tc++) {
            n = Integer.parseInt(br.readLine());
            int sum = 0;
            coins = new int[n][2];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int coin = coins[i][0] = Integer.parseInt(st.nextToken());
                int cnt = coins[i][1] = Integer.parseInt(st.nextToken());
                sum += coin * cnt;
            }
            if (sum % 2 != 0) sb.append(0);
            else sb.append(solve(sum/2));
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
    static int solve(int mid){
        int[] dp = new int[mid + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            int[] coin=coins[i];
            dp[0] = 0;
            for (int j = 0; j <= mid; j++) {
                if (dp[j] == Integer.MAX_VALUE) continue;
                if (j + coin[0] <= mid && dp[j] < coin[1] ) {
                    dp[j + coin[0]] = Math.min(dp[j + coin[0]], dp[j] + 1);
                }
                dp[j] = 0;
            }
        }
        if (dp[mid] != Integer.MAX_VALUE) return 1;
        else return 0;
    }
}