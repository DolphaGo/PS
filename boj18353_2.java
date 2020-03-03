import java.io.*;
import java.util.*;

public class boj18353_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] data = new int[n];
		int[] dp=new int[n];
		for (int i = 0; i < n; i++)
			data[i] = Integer.parseInt(st.nextToken());

		dp[0]=1;
		int res=0;
		for (int i = 1; i < n; i++) {
			dp[i]=1;
			for(int j=0;j<i;j++) {
				if(data[j]>data[i]&&dp[j]+1>dp[i]) dp[i]=dp[j]+1;
			}
			res=Math.max(dp[i], res);
		}
		System.out.println(n-res);
	}
}
