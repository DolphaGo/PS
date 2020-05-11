import java.util.*;
import java.io.*;

public class boj15683_0511 {
	static int h, w, answer, all;
	static int map[][];
	static ArrayList<int[]> cctv = new ArrayList<int[]>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		all = 0;
		for (int y = 0; y < h; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < w; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				if (map[y][x] == 0)
					++all;
				else if (map[y][x] != 6) {
					cctv.add(new int[] { y, x, map[y][x] });
				}
			}
		}
		answer = Integer.MAX_VALUE;
		go(0, 0);
		System.out.println(answer);
	}

	// 북, 동, 남, 서
	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };

	static int cam[][][] = { { { 0 }, { 1 }, { 2 }, { 3 } }, { { 0, 2 }, { 1, 3 } },
			{ { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } }, { { 3, 0, 1 }, { 0, 1, 2 }, { 1, 2, 3 }, { 2, 3, 0 } },
			{ { 0, 1, 2, 3 } } };
	static Stack<int[]> stack = new Stack<int[]>();

	// 백트래킹으로 완전탐색 구현하자.
	static void go(int v, int remove) {
		if (v == cctv.size()) {
			answer = Math.min(answer, all - remove);
			return;
		}
		int cnt = 0;
		int[] now = cctv.get(v);
		int y = now[0];
		int x = now[1];
		int type=now[2]-1;
		for (int j = 0; j < cam[type].length; j++) {
			cnt = 0;
			for (int i = 0; i < cam[type][j].length; i++) {
				int dir = cam[type][j][i];
				int ny = y + dy[dir];
				int nx = x + dx[dir];
				while (ny >= 0 && nx >= 0 && ny < h && nx < w && map[ny][nx] != 6) {
					if (map[ny][nx] == 0) {
						++cnt;
						stack.add(new int[] { ny, nx });
						map[ny][nx] = 7;
					}
					ny += dy[dir];
					nx += dx[dir];
				}
			}
			go(v + 1, remove + cnt);
			reload(cnt);
		}
	}

	static void reload(int cnt) {
		for (int i = 0; i < cnt; i++) {
			int[] pop = stack.pop();
			map[pop[0]][pop[1]] = 0;
		}
	}
}