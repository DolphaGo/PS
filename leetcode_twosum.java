import java.io.*;
import java.util.*;

public class Main {
	static final int M =1000000000;
	static int n,k;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());

		n=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		dp=new int[k+1][n+1];
		Arrays.fill(dp[1],1);

		for (int i = 1; i <= k; i++) { // 개수
			for (int j = 0; j <= n; j++) { // 숫자 j를 만들기 위해
				for (int l = 0; l <= j; l++) { // 이전 숫자를 계산하기 위해
					dp[i][j] += dp[i - 1][j - l];
					dp[i][j]%=M;
				}
			}
		}
		System.out.println(dp[k][n]);
	}
}