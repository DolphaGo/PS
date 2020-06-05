import java.io.*;
import java.util.*;

class swea1953_0605 {
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static int type[][] = { {}, { 0, 1, 2, 3 }, { 0, 1 }, { 2, 3 }, { 0, 3 }, { 1, 3 }, { 1, 2 }, { 0, 2 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			int map[][] = new int[h][w];
			for (int y = 0; y < h; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < w; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}

			boolean visit[][] = new boolean[h][w];
			int answer = 1;
			Queue<int[]> q = new LinkedList<int[]>();
			q.add(new int[] { r, c, 1 });
			visit[r][c] = true;
			while (!q.isEmpty()) {
				int[] p = q.poll();
				int y = p[0];
				int x = p[1];
				int time = p[2];
				if (time >= t)
					continue;
				int idx = map[y][x];
				for (int i = 0; i < type[idx].length; i++) {
					int dir = type[idx][i];
					int ny = y + dy[dir];
					int nx = x + dx[dir];
					if (ny >= 0 && nx >= 0 && ny < h && nx < w && !visit[ny][nx]) {
						boolean chk = false;
						int nidx = map[ny][nx];
						for (int j = 0; j < type[nidx].length; j++) {
							int ddir = type[nidx][j];
							if (isPossible(dir, ddir)) {
								chk = true;
								break;
							}
						}
						if (chk) {
							visit[ny][nx] = true;
							q.add(new int[] { ny, nx, time + 1 });
							++answer;
						}
					}
				}
			}
			sb.append("#" + tc + " " + answer + "\n");
		}
		System.out.print(sb);
	}

	// a와 b가 서로 연결되어 이동할 수 있는지 확인하는 함수
	static boolean isPossible(int a, int b) {
		if ((a == 0 && b == 1) || (a == 1 && b == 0) || (a == 2 && b == 3) || (a == 3 && b == 2))
			return true;
		else
			return false;
	}
}