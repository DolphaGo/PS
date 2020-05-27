

import java.io.*;
import java.util.*;

public class boj13460_0527 {
	static char map[][];
	static int h, w, answer;
	static boolean visit[][][][];
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new char[h][w];
		visit = new boolean[h][w][h][w];
		int red[] = new int[2];
		int blue[] = new int[2];
		for (int y = 0; y < h; y++) {
			map[y] = br.readLine().toCharArray();
			for (int x = 0; x < w; x++) {
				if (map[y][x] == 'B' || map[y][x] == 'R') {
					if (map[y][x] == 'B') {
						blue[0] = y;
						blue[1] = x;
					} else {
						red[0] = y;
						red[1] = x;
					}
					map[y][x] = '.';
				}
			}
		}
		answer = Integer.MAX_VALUE;
		visit[red[0]][red[1]][blue[0]][blue[1]] = true;
		go(0, red, blue);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	static void go(int v, int[] red, int[] blue) {
		if(v>10) return;
		if (map[red[0]][red[1]] == 'O' && map[blue[0]][blue[1]] != 'O') {
			answer = Math.min(v, answer);
			return;
		} else if (map[blue[0]][blue[1]] == 'O')
			return;

		
		int nred[] = new int[2];
		int nblue[] = new int[2];
		for (int i = 0; i < 4; i++) {
			nred[0] = red[0];
			nred[1] = red[1];
			nblue[0] = blue[0];
			nblue[1] = blue[1];
			while (isRange(nred)) {
				nred[0] += dy[i];
				nred[1] += dx[i];
			}
			while (isRange(nblue)) {
				nblue[0] += dy[i];
				nblue[1] += dx[i];
			}
			// 벽이나, O 지점에서 멈췄을 것임.
			if (map[nred[0]][nred[1]] == '#') {
				nred[0] -= dy[i];
				nred[1] -= dx[i];
			}
			if (map[nblue[0]][nblue[1]] == '#') {
				nblue[0] -= dy[i];
				nblue[1] -= dx[i];
			}
			if (nred[0] == nblue[0] && nred[1] == nblue[1] && map[nred[0]][nred[1]] == '.') {
				int rdis = getdis(nred, red);
				int bdis = getdis(nblue, blue);
				if (rdis > bdis) {
					nred[0] -= dy[i];
					nred[1] -= dx[i];
				} else {
					nblue[0] -= dy[i];
					nblue[1] -= dx[i];
				}
			}
			if (!visit[nred[0]][nred[1]][nblue[0]][nblue[1]]) {
				visit[nred[0]][nred[1]][nblue[0]][nblue[1]] = true;
				go(v + 1, nred, nblue);
				visit[nred[0]][nred[1]][nblue[0]][nblue[1]] = false;
			}
		}
	}

	static boolean isRange(int[] a) {
		if (a[0] >= 1 && a[0] < h - 1 && a[1] >= 1 && a[1] < w - 1 && map[a[0]][a[1]]=='.')
			return true;
		else
			return false;
	}

	static int getdis(int[] a, int[] b) {
		return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
	}
}