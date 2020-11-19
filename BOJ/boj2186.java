import java.util.*;
import java.io.*;

public class Main {
	static int h,w,K;
	static char[][] map;
	static char[] target; 
	static int dp[][][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		h=Integer.parseInt(st.nextToken());
		w=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		map=new char[h][w];
		dp=new int[h][w][80];
		for(int y=0;y<h;y++) {
			map[y]=br.readLine().toCharArray();
			for(int x=0;x<w;x++) {
				Arrays.fill(dp[y][x], -1);
			}
		}
		target=br.readLine().toCharArray();
		int answer=0;
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				if(target[0]==map[y][x]) {
					answer+=dfs(y,x,1);
				}
			}
		}
		System.out.println(answer);
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static int dfs(int y,int x,int v) {
		if(v==target.length) return 1;
		if(dp[y][x][v]!=-1) return dp[y][x][v];
		dp[y][x][v]=0;
		for(int k=1;k<=K;k++) {
			for(int i=0;i<4;i++) {
				int ny=y+dy[i]*k;
				int nx=x+dx[i]*k;
				if(ny<0||nx<0||ny>=h||nx>=w) continue;
				if(map[ny][nx] != target[v]) continue;

				dp[y][x][v]+=dfs(ny,nx,v+1);
			
			}
		}
		return dp[y][x][v];
	}
}