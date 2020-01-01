import java.util.*;
import java.io.*;

public class boj17144 {
	static int h,w,up,down;
	static int map[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		h=Integer.parseInt(st.nextToken());
		w=Integer.parseInt(st.nextToken());
		int T=Integer.parseInt(st.nextToken());
		map=new int[h][w];
		
		up=-1;
		down=-1;
		for(int y=0;y<h;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<w;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
				if(map[y][x]==-1) {
					if(up==-1) up=y;
					else down=y;
				}
			}
		}
		for(int t=1;t<=T;t++) {
			spread();
			clean();
		}
		int answer=0;
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				if(map[y][x]!=-1) answer+=map[y][x];
			}
		}
		System.out.println(answer);
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static void spread() {
		int[][] newMap=new int[h][w];
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				if(map[y][x]==-1) newMap[y][x]=-1;
				else {
					int cnt=0;
					for(int i=0;i<4;i++) {
						int ny=y+dy[i];
						int nx=x+dx[i];
						if(ny>=0&&nx>=0&&ny<h&&nx<w&&map[ny][nx]!=-1) {
							newMap[ny][nx]+=map[y][x]/5;
							++cnt;
						}
					}
					newMap[y][x]+=(map[y][x]-cnt*(map[y][x]/5));
				}
			}
		}
		map=newMap;
	}
	static void clean() {
		for(int y=up-2;y>=0;--y) map[y+1][0]=map[y][0];
		for(int x=1;x<w;++x) map[0][x-1]=map[0][x];
		for(int y=1;y<=up;++y) map[y-1][w-1]=map[y][w-1];
		for(int x=w-2;x>=1;--x) map[up][x+1]=map[up][x];
		map[up][1]=0;
		
		for(int y=down+2;y<h;++y) map[y-1][0]=map[y][0];
		for(int x=1;x<w;++x) map[h-1][x-1]=map[h-1][x];
		for(int y=h-2;y>=down;--y) map[y+1][w-1]=map[y][w-1];
		for(int x=w-2;x>=1;--x) map[down][x+1]=map[down][x];
		map[down][1]=0;
	}
}
