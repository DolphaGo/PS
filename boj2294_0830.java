import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
        int[] coins=new int[n];
        int[] dp=new int[k+1];
        Arrays.fill(dp,Integer.MAX_VALUE/2);
        for(int i=0;i<n;i++){
            coins[i]=Integer.parseInt(br.readLine());
            if(coins[i]<=k) dp[coins[i]]=1;
        }

        for(int i=0;i<n;i++){
            int coin=coins[i];
            for(int j=1;j<=k;j++){
                if(j>=coin&&dp[j]>dp[j-coin]+1){
                    dp[j]=dp[j-coin]+1;
                }
            }
        }
        if(dp[k]==Integer.MAX_VALUE/2) System.out.println(-1);
        else System.out.println(dp[k]);
    }
}