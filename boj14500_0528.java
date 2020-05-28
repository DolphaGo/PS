import java.io.*;
import java.util.*;

public class boj14500_0528 {
	static int answer,h,w;
	static int[][] map;
	static boolean[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		h=Integer.parseInt(st.nextToken());
		w=Integer.parseInt(st.nextToken());
		map=new int[h][w];
		visit=new boolean[h][w];
		for(int y=0;y<h;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<w;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		answer=0;
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				visit[y][x]=true;
				go(y,x,1,map[y][x]);
				visit[y][x]=false;
				fk(y,x);
			}
		}
		System.out.println(answer);
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static void go(int y,int x,int v,int sum) {
		if(v==4) {
			answer=answer<sum?sum:answer;
			return;
		}
		
		for(int i=0;i<4;i++) {
			int ny=y+dy[i];
			int nx=x+dx[i];
			if(ny>=0&&nx>=0&&ny<h&&nx<w&&!visit[ny][nx]) {
				visit[ny][nx]=true;
				go(ny,nx,v+1,sum+map[ny][nx]);
				visit[ny][nx]=false;
			}
		}
	}
	static void fk(int y,int x) {
		//ㅏ
		int sum=map[y][x];
		for(int yy=y-1;yy<=y+1;yy++) {
			if(yy>=0&&yy<h&&x-1>=0) sum+=map[yy][x-1];
		}
		answer=answer<sum?sum:answer;
		//ㅓ
		sum=map[y][x];
		for(int yy=y-1;yy<=y+1;yy++) {
			if(yy>=0&&yy<h&&x+1<w) sum+=map[yy][x+1];
		}
		answer=answer<sum?sum:answer;
		//ㅜ
		sum=map[y][x];
		for(int xx=x-1;xx<=x+1;xx++) {
			if(xx>=0&&xx<w&&y-1>=0) sum+=map[y-1][xx];
		}
		answer=answer<sum?sum:answer;
		//ㅗ
		sum=map[y][x];
		for(int xx=x-1;xx<=x+1;xx++) {
			if(xx>=0&&xx<w&&y+1<h) sum+=map[y+1][xx];
		}
		answer=answer<sum?sum:answer;
	}
}