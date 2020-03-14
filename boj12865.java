import java.util.*;
import java.io.*;

public class boj12865 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		int dp[][]=new int[n+1][k+1];
		
		int w[]=new int[n+1];
		int v[]=new int[n+1];
		//Knapsack 문제
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			w[i]=Integer.parseInt(st.nextToken());
			v[i]=Integer.parseInt(st.nextToken());
		}
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=k;j++) {
				if(w[i]<=j) {//현재 무게를 담을 수 있을 때
					dp[i][j]=Math.max(dp[i-1][j-w[i]]+v[i], dp[i-1][j]);
				}else dp[i][j]=dp[i-1][j];
			}
		}
		System.out.println(dp[n][k]);
	}
}
