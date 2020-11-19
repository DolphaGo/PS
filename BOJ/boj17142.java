import java.util.*;
import java.io.*;

public class boj17142 {
	static int n, m, answer, zero;
	static int map[][];
	static ArrayList<int[]> virus = new ArrayList<int[]>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		zero = 0;
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				if (map[y][x] == 2)
					virus.add(new int[] { y, x });
				if (map[y][x] == 0)
					++zero;
			}
		}
		if (zero == 0) {
			System.out.println(0);
		} else {
			answer = Integer.MAX_VALUE;
			go(0, 0);
			System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
		}
	}

	static ArrayList<int[]> list = new ArrayList<int[]>();

	static void go(int v, int select) {
		if (select == m) {
			int res = Test();
			answer = Math.min(answer, res);

		}
		if (v == virus.size())
			return;

		list.add(virus.get(v));
		go(v + 1, select + 1);
		list.remove(list.size() - 1);
		go(v + 1, select);
	}

	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	static int Test() {
		int[][] dmap = new int[n][n];
		for (int y = 0; y < n; y++) {
			dmap[y] = map[y].clone();
		}
		Queue<int[]> q = new LinkedList<int[]>();
		for (int[] val : list) {
			dmap[val[0]][val[1]] = 3;
			q.add(new int[] { val[0], val[1], 0 });
		}
		int clean = 0;
		while (!q.isEmpty()) {
			int[] p = q.poll();
			int y = p[0];
			int x = p[1];
			int t = p[2];
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (ny >= 0 && nx >= 0 && ny < n && nx < n && (dmap[ny][nx] == 0 || dmap[ny][nx] == 2)) {
					if (dmap[ny][nx] == 0)
						++clean;
					dmap[ny][nx] = 3;
					if (clean == zero) {
						return t + 1;
					} else
						q.add(new int[] { ny, nx, t + 1 });
				}
			}
		}
		return Integer.MAX_VALUE;
	}
}