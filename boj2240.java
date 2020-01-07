import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int[][] dp=new int[t+1][w+1];
		int[] plum= new int[t+1];
		for (int i = 1; i <= t; i++) plum[i] = Integer.parseInt(br.readLine());  
		int ans=0;
		for(int i=1;i<=t;i++) {
			if(plum[i]==1) dp[i][0]=dp[i-1][0]+1;
			else dp[i][0]=dp[i-1][0];
			for(int j=1;j<=w&&j<=i;j++) {
				//j가 짝수면 1번 나무 밑에 있는 거, 홀수면 2번 나무 밑에 있는 것
				if((j%2==0&&plum[i]==1)||(j%2==1&&plum[i]==2)) dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-1])+1;
				else dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-1]);
			}
		}
		for(int i=0;i<=w;i++) ans=ans>dp[t][i]?ans:dp[t][i];

		System.out.println(ans);
	}
}