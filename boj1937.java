import java.util.*;
import java.io.*;

public class boj1937 {
	static int n,answer;
	static int[][] map;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		dp=new int[n][n];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		answer=Integer.MIN_VALUE;
		for(int y=0;y<n;y++) {
			for(int x=0;x<n;x++) {
				go(y,x);
			}
		}
		System.out.println(answer);

	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	
	static int go(int y,int x) {
		
		if(dp[y][x]!=0) return dp[y][x];
		dp[y][x]=1;
		for(int i=0;i<4;i++) {
			int ny=y+dy[i];
			int nx=x+dx[i];
			if(ny>=0&&nx>=0&&ny<n&&nx<n&&map[ny][nx]>map[y][x]) {
				dp[y][x]=Math.max(go(ny,nx)+1, dp[y][x]);
			}
		}
		
		answer=answer<dp[y][x]?dp[y][x]:answer;
		return dp[y][x];
	}
	
}