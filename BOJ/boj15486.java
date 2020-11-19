import java.util.*;
import java.io.*;
public class Main {
	static int dp[];
	static int table[][];
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());
		table=new int[n+1][2];
		dp=new int[n+2];
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			int t=Integer.parseInt(st.nextToken());
			int p=Integer.parseInt(st.nextToken());
			table[i][0]=t;
			table[i][1]=p;
		}
		int max=0;
		for(int i=n;i>=1;i--) {
			dp[i]=max;
			if(i+table[i][0]-1<=n) dp[i]=Math.max(table[i][1]+dp[i+table[i][0]],dp[i]);
			max=Math.max(max,dp[i]);
		}
		System.out.println(dp[1]);
	}
}
