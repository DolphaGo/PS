import java.io.*;
import java.util.*;

public class boj15684 {
	static int n, m, h;
	static int[][] map;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new int[h][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b] = 1;// 우측
			map[a][b + 1] = 2;// 좌측
		}
		answer = 4;
		go(0, 0);
		System.out.println(answer == 4 ? -1 : answer);
	}

	static void go(int sy, int conn) {
		if (conn >= answer)
			return;
		if (OK()) {
			answer = Math.min(answer, conn);
			return;
		}
		for (int y = sy; y < h; y++) {
			for (int x = 0; x < n - 1; x++) {
				if (map[y][x + 1] == 0 && map[y][x] == 0 && conn < 3) {
					map[y][x] = 1;
					map[y][x + 1] = 2;
					go(y, conn + 1);
					map[y][x + 1] = 0;
					map[y][x] = 0;
				}
			}
		}
	}

	static boolean OK() {
		for (int i = 0; i < n; i++) {
			int sy = 0;
			int sx = i;
			while (sy < h) {
				if (map[sy][sx] == 1)
					++sx;
				else if (map[sy][sx] == 2)
					--sx;
				++sy;
			}
			if (sx != i)
				return false;
		}
		return true;
	}
}