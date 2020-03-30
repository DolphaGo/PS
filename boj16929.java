import java.util.*;
import java.io.*;

public class boj16929 {
	static int h,w;
	static boolean flag;
	static char[][] map;
	static boolean[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		h=Integer.parseInt(st.nextToken());
		w=Integer.parseInt(st.nextToken());
		map=new char[h][w];
		visit=new boolean[h][w];
		for(int y=0;y<h;y++) {
			map[y]=br.readLine().toCharArray();
		}
		flag=false;
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				for(int k=0;k<h;k++) Arrays.fill(visit[k],false);
				visit[y][x]=true;
				go(y,x,1,y,x);
				if(flag) {
					System.out.println("Yes");
					return;
				}
			}
		}
		System.out.println("No");
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static void go(int y,int x,int cnt,int sy,int sx) {
		for(int i=0;i<4;i++) {
			int ny=y+dy[i];
			int nx=x+dx[i];
			if(ny>=0&&nx>=0&&ny<h&&nx<w&&map[ny][nx]==map[y][x]) {
				if(!visit[ny][nx]) {
					visit[ny][nx]=true;
					go(ny,nx,cnt+1,sy,sx);
				}else {
					if(cnt>=4&&ny==sy&&nx==sx) {
						flag=true;
						return;
					}
				}
			}
		}
	}
}