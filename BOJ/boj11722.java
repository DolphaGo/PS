import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr,dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n=Integer.parseInt(br.readLine());
        arr=new int[n];
        dp=new int[n];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        int answer=0;
        for(int i=0;i<n;i++){
            if(dp[i]==0) answer=Math.max(answer,go(i));
        }

        System.out.println(answer);
    }
    static int go(int v){
        if(dp[v]!=0) return dp[v];
        dp[v]=1;
        for(int i=v+1;i<n;i++){
            if(arr[v]>arr[i]){
                dp[v]=Math.max(dp[v],go(i)+1);
            }
        }
        return dp[v];
    }
}