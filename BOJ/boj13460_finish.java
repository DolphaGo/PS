import java.util.*;
import java.io.*;

public class boj13460_finish {
	static int h, w, answer;
	static char[][] map;
	static boolean[][][][] visit;
  
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new char[h][w];
		visit=new boolean[h][w][h][w];
		int[] R = new int[2];
		int[] B = new int[2];
		for (int y = 0; y < h; y++) {
			map[y] = br.readLine().toCharArray();
			for (int x = 0; x < w; x++) {
				if (map[y][x] == 'R') {
					R[0] = y;
					R[1] = x;
					map[y][x] = 'R';
				} else if (map[y][x] == 'B') {
					B[0] = y;
					B[1] = x;
					map[y][x] = 'B';
				}
			}
		}
		visit[R[0]][R[1]][B[0]][B[1]] = true;
		answer = Integer.MAX_VALUE;
		go(0, R, B);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	static void go(int v, int[] R, int[] B) {
		if (v > 10 || v>=answer) return;
		if (map[R[0]][R[1]] == 'O') {
			answer = Math.min(v, answer);
		}

		for (int i = 0; i < 4; i++) {
			int[] nR = move(i, R);
			int[] nB = move(i, B);
			//이동 후, 같은 위치에 있었다면(출구가 아닌 곳에서)
			if (nR[0] == nB[0] && nR[1] == nB[1] && map[nR[0]][nR[1]]!='O') {
				int dR = diff(R, nR);
				int dB = diff(B, nB);
				if (dR > dB) {
					nR[0] -= dy[i];
					nR[1] -= dx[i];
				} else {
					nB[0] -= dy[i];
					nB[1] -= dx[i];
				}
			}
			//Blue가 빠지지 않은 상태 && 방문했던 곳이 아닌 지점에서만 다음 탐색을 진행하도록 한다.
			if (map[nB[0]][nB[1]] != 'O' && !visit[nR[0]][nR[1]][nB[0]][nB[1]]) {
				visit[nR[0]][nR[1]][nB[0]][nB[1]]=true;
				go(v+1, nR, nB);
				visit[nR[0]][nR[1]][nB[0]][nB[1]]=false;
			}
		}
	}

	static int[] move(int i, int[] ball) {
		int ny = ball[0];
		int nx = ball[1];
		while (true) {
			ny += dy[i];
			nx += dx[i];
			if (map[ny][nx] == '#') {
				return new int[] { ny - dy[i], nx - dx[i] };
			} else if (map[ny][nx] == 'O')
				return new int[] { ny, nx };
		}
	}

	static int diff(int[] a1, int[] a2) {
		return Math.abs(a1[0] - a2[0]) + Math.abs(a1[1] - a2[1]);
	}
}