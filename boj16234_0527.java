import java.io.*;
import java.util.*;

class boj16234_0527 {
	static int n, L, R;
	static int map[][];
	static Queue<int[]> q = new LinkedList<int[]>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = 0;
		while (true) {
			if (go()) {
				while (!q.isEmpty()) {
					int[] p = q.poll();
					map[p[0]][p[1]] = p[2];
				}
				++answer;
			} else
				break;
		}
		System.out.println(answer);
	}

	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	static boolean go() {
		boolean flag = false;
		boolean visit[][] = new boolean[n][n];
		Queue<int[]> tmp = new LinkedList<int[]>();
		Queue<int[]> trace = new LinkedList<int[]>();
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				if (!visit[y][x]) {
					int cnt = 1;
					int val = map[y][x];
					tmp.add(new int[] { y, x });
					trace.add(new int[] { y, x });
					visit[y][x] = true;
					while (!tmp.isEmpty()) {
						int[] p = tmp.poll();
						for (int i = 0; i < 4; i++) {
							int ny = p[0] + dy[i];
							int nx = p[1] + dx[i];
							if (isOk(p[0], p[1], ny, nx) && !visit[ny][nx]) {
								visit[ny][nx] = true;
								++cnt;
								val += map[ny][nx];
								tmp.add(new int[] { ny, nx });
								trace.add(new int[] { ny, nx });
							}
						}
					}
					int res = val / cnt;
					if (trace.size() > 1)
						flag = true;
					while (!trace.isEmpty()) {
						int[] p = trace.poll();
						q.add(new int[] { p[0], p[1], res });
					}
				}
			}
		}

		return flag;
	}

	static boolean isOk(int y, int x, int ny, int nx) {
		if (ny >= 0 && nx >= 0 && ny < n && nx < n) {
			int diff = Math.abs(map[y][x] - map[ny][nx]);
			if (L <= diff && diff <= R)
				return true;
			else
				return false;
		} else
			return false;
	}
}