import java.io.*;
import java.util.*;

public class boj17144_0601 {
	static int h,w;
	static int map[][];
	static ArrayList<Integer> cleaner=new ArrayList<Integer>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		h=Integer.parseInt(st.nextToken());
		w=Integer.parseInt(st.nextToken());
		map=new int[h][w];
		int t=Integer.parseInt(st.nextToken());
		
		for(int y=0;y<h;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<w;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
				if(map[y][x]==-1) {
					cleaner.add(y);
				}
			}
		}
		
		while(t-->0) run();
		
		int answer=0;
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				if(map[y][x]>0) answer+=map[y][x];
			}
		}
		System.out.println(answer);
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static void run() {
		int[][] add=new int[h][w];
		//미세먼지 확산시키기
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				if(map[y][x]>0) {
					for(int i=0;i<4;i++) {
						int ny=y+dy[i];
						int nx=x+dx[i];
						if(ny>=0&&nx>=0&&ny<h&&nx<w&&map[ny][nx]!=-1) {
							add[ny][nx]+=map[y][x]/5;
							add[y][x]-=map[y][x]/5;
						}
					}
				}
			}
		}
		//한번에 확산
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				map[y][x]+=add[y][x];
			}
		}
		//공기청정기 작동
		clean(cleaner.get(0),1); //반시계
		clean(cleaner.get(1),-1); //시계
	}
	static void clean(int sy, int dir) {
		switch(dir) {
		case 1:
			for(int y=sy-1;y>=1;y--) map[y][0]=map[y-1][0];
			for(int x=0;x<w-1;x++) map[0][x]=map[0][x+1];
			for(int y=0;y<sy;y++) map[y][w-1]=map[y+1][w-1];
			for(int x=w-1;x>=2;x--) map[sy][x]=map[sy][x-1];
			map[sy][1]=0;
			break;
		case -1:
			for(int y=sy+1;y<h-1;y++) map[y][0]=map[y+1][0];
			for(int x=0;x<w-1;x++) map[h-1][x]=map[h-1][x+1];
			for(int y=h-1;y>sy;y--) map[y][w-1]=map[y-1][w-1];
			for(int x=w-1;x>=2;x--) map[sy][x]=map[sy][x-1];
			map[sy][1]=0;
			break;
		}
	}
}