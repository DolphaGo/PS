import java.util.*;
import java.io.*;

public class boj14503 {
	static int h, w, cnt;
	static int map[][];
	static boolean visit[][];
	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		map = new int[h][w];
		visit = new boolean[h][w];
		for (int y = 0; y < h; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < w; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		cnt = 1;
		visit[r][c]=true;
		go(r,c,d);
		System.out.println(cnt);
	}
	static void go(int y,int x,int dir) {
		boolean flag=false;
		for(int i=dir+3;i>=dir;--i) {
			int nd=i%4;
			int ny=y+dy[nd];
			int nx=x+dx[nd];
			if(ny>=0&&nx>=0&&ny<h&&nx<w&&map[ny][nx]==0&&!visit[ny][nx]) {
				visit[ny][nx]=true;
				++cnt;
				go(ny,nx,nd);
				flag=true;
				break;
			}
		}
		if(!flag) {
			int ny=y-dy[dir];
			int nx=x-dx[dir];
			if(ny>=0&&nx>=0&&ny<h&&nx<w&&map[ny][nx]==0) {
				go(ny,nx,dir);
			}else return;
		}
	}
}