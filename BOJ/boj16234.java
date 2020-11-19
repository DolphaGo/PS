import java.io.*;
import java.util.*;

public class Main {
	static int n, l, r, answer;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		while (true) {
			if (go()) answer++;
			else break;
		}
		System.out.println(answer);
	}

	static boolean go() {
		boolean flag = false;
		Queue<int[]> q = new LinkedList<int[]>();
		Queue<int[]> l = new LinkedList<int[]>();
		int dy[] = { -1, 1, 0, 0 };
		int dx[] = { 0, 0, 1, -1 };
		boolean v[][] = new boolean[n][n];
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				if (v[y][x]) continue;
				v[y][x] = true;
				l.add(new int[] { y, x });
				q.add(new int[] { y, x });
				int sum = map[y][x];
				while (!q.isEmpty()) {
					int[] p = q.poll();
					for (int i = 0; i < 4; i++) {
						int ny = p[0] + dy[i];
						int nx = p[1] + dx[i];
						if (isRange(ny, nx) && !v[ny][nx] && Condition(map[p[0]][p[1]], map[ny][nx])) {
							v[ny][nx] = true;
							sum += map[ny][nx];
							l.add(new int[] { ny, nx });
							q.add(new int[] { ny, nx });
						}
					}
				}
				if (l.size() > 1) {
					flag = true;
					int avg = sum / l.size();
					while (!l.isEmpty()) {
						int[] p = l.poll();
						map[p[0]][p[1]] = avg;
					}
				} else l.clear();
			}
		}
		return flag;
	}

	static boolean isRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < n && x < n) return true;
		return false;
	}

	static boolean Condition(int a, int b) {
		int tar = Math.abs(a - b);
		if (l <= tar && tar <= r) return true;
		return false;
	}
}
