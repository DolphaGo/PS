import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        int[][] dp=new int[N+1][10];
        Arrays.fill(dp[1],1);

        for(int i=2;i<=N;i++){
            int sum=0;
            for(int j=0;j<=9;j++){
                sum=(sum+dp[i-1][j])%10007;
                dp[i][j]=sum;
            }
        }
        int answer=0;
        for(int i=0;i<=9;i++) answer=(answer+dp[N][i])%10007;
        System.out.println(answer);
    }

}