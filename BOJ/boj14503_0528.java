import java.io.*;
import java.util.*;

public class boj14503_0528 {
	static int h, w, answer;
	static int map[][];
	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };
	static boolean visit[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 맵의 크기
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		// 청소기 좌표
		st = new StringTokenizer(br.readLine());
		int sy = Integer.parseInt(st.nextToken());
		int sx = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		// 맵
		map = new int[h][w];
		for (int y = 0; y < h; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < w; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		visit = new boolean[h][w];
		answer = 1;
		visit[sy][sx]=true;
		go(sy, sx, d);
		System.out.println(answer);
	}
	static void go(int y,int x,int d) {
		boolean flag=false;
		for(int i=1;i<=4;i++) {
			int dir=(d-i+4)%4;
			int ny=y+dy[dir];
			int nx=x+dx[dir];
			if(ny>=0&&nx>=0&&ny<h&&nx<w&&map[ny][nx]==0&&!visit[ny][nx]) {
				visit[ny][nx]=true;
				++answer;
				flag=true;
				go(ny,nx,dir);
				break;
			}
		}
		if(!flag) {
			int ny=y-dy[d];
			int nx=x-dx[d];
			if(ny>=0&&nx>=0&&ny<h&&nx<w&&map[ny][nx]!=1) {
				go(ny,nx,d);
			}else return;
		}
	}
}