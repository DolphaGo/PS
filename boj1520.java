import java.util.*;
import java.io.*;

public class boj1520 {
	static int map[][];
	static int dp[][];
	static boolean visit[][];
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static int h,w;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		h=Integer.parseInt(st.nextToken());
		w=Integer.parseInt(st.nextToken());
		map=new int[h][w];
		dp=new int[h][w];
		visit=new boolean[h][w];
		for(int y=0;y<h;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<w;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(go(0,0));
	}
	static int go(int y,int x) {
		if(visit[y][x]) return dp[y][x];
		if(y==h-1&&x==w-1) return dp[y][x]=1;
		for(int i=0;i<4;i++) {
			int ny=y+dy[i];
			int nx=x+dx[i];
			if(ny>=0&&nx>=0&&ny<h&&nx<w&&map[ny][nx]<map[y][x]) {
				dp[y][x]=Math.max(go(ny,nx)+dp[y][x],dp[y][x]);
			}
		}
		visit[y][x]=true;
		return dp[y][x];
	}
}