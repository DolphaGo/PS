import java.io.*;
import java.util.*;

class swea2105 {
	static int n, answer;
	static int[][] map;
	static boolean[] visit;
	static int dy[] = { 1, 1, -1, -1 };
	static int dx[] = { 1, -1, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = 0;
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			for (int y = 0; y < n; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < n; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			visit = new boolean[101];
			for (int y = 0; y < n - 2; y++) {
				for (int x = 1; x < n - 1; x++) {
					visit[map[y][x]] = true;
					go(y, x, 1, y, x, 0);
					visit[map[y][x]] = false;
				}
			}
			if (answer == 0)
				answer = -1;
			sb.append("#" + tc + " " + answer + "\n");
		}
		System.out.print(sb);
	}

	static void go(int y, int x, int len, int sy, int sx, int dir) {
		if (dir < 3) {
			for (int i = dir; i <= dir + 1; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (ny >= 0 && nx >= 0 && ny < n && nx < n) {
					if (!visit[map[ny][nx]]) {
						visit[map[ny][nx]] = true;
						go(ny, nx, len + 1, sy, sx, i);
						visit[map[ny][nx]] = false;
					}else if(ny==sy&&nx==sx&&i==3) {
						answer=answer<len?len:answer;
					}
				}
			}
		}else {
			int ny=y+dy[dir];
			int nx=x+dx[dir];
			if(ny>=0&&nx>=0&&ny<n&&nx<n) {
				if(ny==sy&&nx==sx) {
					answer=answer<len?len:answer;
				}else if(!visit[map[ny][nx]]){
					visit[map[ny][nx]]=true;
					go(ny,nx,len+1,sy,sx,dir);
					visit[map[ny][nx]]=false;
				}
			}
		}
	}
}