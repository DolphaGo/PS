import java.util.*;
import java.io.*;

public class Main {
	static int h, w, cheeze;
	static int[][] map, visit;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static Queue<int[]> q = new LinkedList<int[]>();
	static Queue<int[]> rm = new LinkedList<int[]>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		cheeze = 0; // 치즈 조각을 셀 변수
		map = new int[h][w]; // 사이드에 빈 공간을 줍니다.
		visit = new int[h][w];
		for (int y = 0; y < h; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < w; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				if (map[y][x] == 1)
					++cheeze;
			}
		}

		// init
		q.add(new int[] { 0, 0 });
		visit[0][0] = -1;

		int t = 0;
		while (cheeze > 0) {
			++t;
			check();
			remove();
		}

		System.out.println(t);
	}

	static void remove() {
		while (!rm.isEmpty()) {
			int[] p = rm.poll();
			--cheeze;
			q.add(p);
		}
	}

	static void check() {
		while (!q.isEmpty()) {
			int[] p = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = p[0] + dy[i];
				int nx = p[1] + dx[i];
				if (isRange(ny, nx) && visit[ny][nx] >= 0) {
					if (map[ny][nx] == 1) {
						visit[ny][nx]++;
						// 공기가 2번이상 맞닿은 것들은 제거 대상
						if (visit[ny][nx] >= 2) {
							visit[ny][nx] = -1; // 지움
							rm.add(new int[] { ny, nx });
						}
					} else {
						visit[ny][nx] = -1;
						q.add(new int[] { ny, nx });
					}
				}
			}
		}
	}

	static boolean isRange(int y, int x) {
		return y >= 0 && x >= 0 && y < h && x < w;
	}
}