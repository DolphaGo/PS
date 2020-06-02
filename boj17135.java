import java.io.*;
import java.util.*;

public class boj17135 {
	static int h, w, d, answer;
	static int map[][];
	static int arch[] = new int[3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		map = new int[h + 1][w];
		for (int y = 0; y < h; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < w; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		answer = 0;
		go(0, 0);
		System.out.println(answer);
	}

	static void go(int v, int select) {
		if (select == 3) {
			int kill = 0;
			int[][] dummymap = new int[h][w];
			for (int y = 0; y < h; y++)
				dummymap[y] = map[y].clone();
			for (int i = h; i >= 1; i--) {// 실제 아처가 있는 위치
				kill += killEnemy(dummymap, i);
			}
			answer = answer < kill ? kill : answer;
			return;
		}
		if (v == w)
			return;

		arch[select] = v;
		go(v + 1, select + 1);
		go(v + 1, select);
	}

	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
		public int compare(int[] o1, int[] o2) {
			if (o1[2] == o2[2])
				return Integer.compare(o1[1], o2[1]);
			return Integer.compare(o1[2], o2[2]);
		}
	});

	static int killEnemy(int[][] map, int h) {
		Queue<int[]> q = new LinkedList<int[]>();
		Queue<int[]> killList = new LinkedList<int[]>();
		for (int i = 0; i < 3; i++) {
			int y = h;// 실제 아처가 있는 위치
			int x = arch[i];
			boolean visit[][] = new boolean[h][w];
			pq.clear();
			q.add(new int[] { y, x, 0 });
			while (!q.isEmpty()) {
				int[] p = q.poll();
				for (int j = 0; j < 4; j++) {
					int ny = p[0] + dy[j];
					int nx = p[1] + dx[j];
					// 잡을 수 있는 사정거리 내에서만 탐색
					if (ny >= 0 && nx >= 0 && ny < h && nx < w && !visit[ny][nx] && p[2] + 1 <= d) {
						visit[ny][nx] = true;
						if (map[ny][nx] == 1) {
							// 적을 발견했으면 탐색 종료
							pq.add(new int[] { ny, nx, p[2] + 1 });
						} else
							q.add(new int[] { ny, nx, p[2] + 1 });
					}
				}
			}
			// 잡을 수 있는 것이 있다면
			if (pq.size() > 0)
				killList.add(pq.poll());
		}
		int cnt = 0;
		while (!killList.isEmpty()) {
			int[] p = killList.poll();
			if (map[p[0]][p[1]] == 1) {
				map[p[0]][p[1]] = 0;
				++cnt;
			}
		}
		return cnt;
	}
}