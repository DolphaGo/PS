import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        int[] card=new int[n+1];
        int[] dp=new int[n+1];
        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) {
            card[i]=Integer.parseInt(st.nextToken());
        }
        for(int i=1;i<=n;i++){
            for(int j=i;j<=n;j++){
                int val = dp[j - i] + card[i];
                if(dp[j]< val) dp[j]= val;
            }
        }
        System.out.println(dp[n]);
    }
}