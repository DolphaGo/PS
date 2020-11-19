import java.io.*;
import java.util.*;

public class boj2146 {
	static int n, answer;
	static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] rmap = new int[n][n];
		int num = Render(rmap, map);
		answer = Integer.MAX_VALUE;
		for (int i = 1; i <= num; i++) {
			int min = go(rmap, i);
			answer = answer > min ? min : answer;
		}
		System.out.println(answer);
	}

	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	static int go(int[][] map, int pivot) {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean visit[][] = new boolean[n][n];
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				if (map[y][x] == pivot) {
					visit[y][x] = true;
					q.add(new int[] { y, x, 0 });
				}
			}
		}
		while (!q.isEmpty()) {
			int[] p = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = p[0] + dy[i];
				int nx = p[1] + dx[i];
				if (ny >= 0 && nx >= 0 && ny < n && nx < n && !visit[ny][nx]) {
					visit[ny][nx] = true;
					if (map[ny][nx] == 0)
						q.add(new int[] { ny, nx, p[2] + 1 });
					else {
						return p[2];
					}
				}
			}
		}
		return Integer.MAX_VALUE;
	}

	static int Render(int[][] rmap, int[][] map) {
		boolean visit[][] = new boolean[n][n];
		Queue<int[]> q = new LinkedList<int[]>();
		int cnt = 0;
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				if (map[y][x] == 1 && !visit[y][x]) {
					visit[y][x] = true;
					rmap[y][x] = ++cnt;
					q.add(new int[] { y, x });
					while (!q.isEmpty()) {
						int[] p = q.poll();
						for (int i = 0; i < 4; i++) {
							int ny = p[0] + dy[i];
							int nx = p[1] + dx[i];
							if (ny >= 0 && nx >= 0 && ny < n && nx < n && map[ny][nx] == 1 && !visit[ny][nx]) {
								visit[ny][nx] = true;
								rmap[ny][nx] = cnt;
								q.add(new int[] { ny, nx });
							}
						}
					}
				}
			}
		}
		return cnt;
	}
}