import java.io.*;
import java.util.*;

class swea5648 {
	static int dy[] = { 1, -1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		Queue<int[]> q = new LinkedList<int[]>();
		Queue<int[]> boom = new LinkedList<int[]>();
		ArrayList<int[]> wait = new ArrayList<int[]>();
		int[][] map = new int[4001][4001];
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int answer = 0;
			int n = Integer.parseInt(br.readLine());
			q.clear();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int d = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				q.add(new int[] { y, x, d, k });
				map[y][x] = k;
			}

			// 0.5초단위로 충돌 분석
			while (!q.isEmpty()) {
				int size = q.size();
				for (int i = 0; i < size; i++) {
					int[] p = q.poll();
					int y = p[0];
					int x = p[1];
					int d = p[2];
					int k = p[3];

					map[y][x] = 0;
					int ny = y + dy[d];
					int nx = x + dx[d];
					if (isRange(ny, nx)) {
						if (map[ny][nx] == 0) {
							map[ny][nx] = k;
							wait.add(new int[] { ny, nx, d, k });
						} else {
							// 충돌 발생한 것임
							map[ny][nx] += k;
							boom.add(new int[] { ny, nx, d, k });
						}
					}
				}
				while (!boom.isEmpty()) {
					int[] p = boom.poll();
					if (map[p[0]][p[1]] != 0) {
						answer += map[p[0]][p[1]];
						map[p[0]][p[1]] = 0;
						for (int i = 0; i < wait.size(); i++) {
							int[] w = wait.get(i);
							if (w[0] == p[0] && w[1] == p[1]) {
								wait.remove(i);
								break;
							}
						}
					}
				}

				for (int i = 0; i < wait.size(); i++) {
					q.add(wait.get(i));
				}
				wait.clear();
				
			}

			sb.append("#" + tc + " " + answer + "\n");
		}
		System.out.print(sb);
	}

	static boolean isRange(int y,int x) {
		if (y >= 0 && x >= 0 && y <= 4000 && x <= 4000)
			return true;
		return false;
	}
}