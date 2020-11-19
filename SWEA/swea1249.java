import java.io.*;
import java.util.*;

class swea1249 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int dy[] = { -1, 1, 0, 0 };
		int dx[] = { 0, 0, 1, -1 };
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			int map[][] = new int[n][n];
			boolean v[][] = new boolean[n][n];
			for (int y = 0; y < n; y++) {
				char temp[] = br.readLine().toCharArray();
				for (int x = 0; x < n; x++) {
					map[y][x] = temp[x] - '0';
				}
			}
			PriorityQueue<int[]> q = new PriorityQueue<int[]>(new Comparator<int[]>() {
				public int compare(int[] o1, int[] o2) {
					return Integer.compare(o1[2], o2[2]);
				}
			});
			q.add(new int[] { 0, 0, 0 });
			v[0][0] = true;
			int answer = 0;
			while (!q.isEmpty()) {
				int[] p = q.poll();
				int y = p[0];
				int x = p[1];
				int len = p[2];
				if (y == n - 1 && x == n - 1) {
					answer = len;
					q.clear();
					break;
				}
				for (int i = 0; i < 4; i++) {
					int ny = y + dy[i];
					int nx = x + dx[i];
					if (ny >= 0 && nx >= 0 && ny < n && nx < n && !v[ny][nx]) {
						v[ny][nx] = true;
						q.add(new int[] { ny, nx, len + map[ny][nx] });
					}
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}
}