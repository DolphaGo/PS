import java.io.*;
import java.util.*;

public class boj9095 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		int dp[]=new int[11];
		dp[1]=1;
		dp[2]=2;
		dp[3]=4;
		for(int tc=1;tc<=T;tc++) {
			int val=Integer.parseInt(br.readLine());
			for(int i=4;i<=val;i++) {
				if(dp[val]!=0) continue;
				dp[i]=dp[i-1]+dp[i-2]+dp[i-3];
			}
			System.out.println(dp[val]);
		}
	}
}
