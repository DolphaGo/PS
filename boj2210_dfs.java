import java.io.*;
import java.util.*;

public class boj2210_dfs {
	static int[][] map;
	static boolean[] visit;
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map=new int[5][5];
		for(int y=0;y<5;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<5;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		visit=new boolean[1000000];
		answer=0;
		
		for(int y=0;y<5;y++) {
			for(int x=0;x<5;x++) {
				go(y,x,1,map[y][x]);
			}
		}
		
		System.out.println(answer);
	}
	static void go(int y,int x,int len,int val) {
		if(len==6) {
			if(!visit[val]) {
				visit[val]=true;
				++answer;
			}
			return;
		}
		for(int i=0;i<4;i++) {
			int ny=y+dy[i];
			int nx=x+dx[i];
			if(ny>=0&&nx>=0&ny<5&&nx<5) {
				int nval=val*10+map[ny][nx];
				go(ny,nx,len+1,nval);
			}
		}
	}
}
