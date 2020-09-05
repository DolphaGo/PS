import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());

        int[] arr=new int[n];
        int[] dp=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=Integer.parseInt(br.readLine());
            dp[i]=1;
        }

        int max=0;
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j]&&dp[i]<dp[j]+1){
                    dp[i]=dp[j]+1;
                }
            }
            max= Math.max(max, dp[i]);
        }
        System.out.println(n-max);
    }
}