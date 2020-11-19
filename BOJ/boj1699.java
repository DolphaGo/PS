import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dp=new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);

        for(int i=1;i<=n;i++){
            for(int j=1;j*j<=i;j++){
                if(j*j==i) {
                    dp[i]=1;
                    break;
                }
                dp[i]=Math.min(dp[i],dp[j*j]+dp[i-j*j]);
            }
        }
        System.out.println(dp[n]);
    }
}