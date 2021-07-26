import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder answer = new StringBuilder();
	static int[][] connect;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		connect = new int[n + 1][n + 1];

		for (int y = 1; y <= n; y++) {
			st = new StringTokenizer(br.readLine());
			int sum = 0;
			for (int x = 1; x <= n; x++) {
				connect[y][x] = Integer.parseInt(st.nextToken());
				sum += connect[y][x];
			}
			if (sum % 2 != 0) {
				System.out.println(-1);
				return;
			}
		}

		int id = 0;
		Queue<int[]>[] q = getQueues(n);

		for (int i = 1; i <= n; i++) {
			for (int j = i + 1; j <= n; j++) {
				while (connect[i][j] > 0) {
					connect[i][j]--;
					id++;
					q[i].add(new int[] { j, id }); // i->j 의 간선 번호
					q[j].add(new int[] { i, id }); // j->i 의 간선 번호
				}
			}
		}

		visit = new boolean[id + 1];
		dfs(q, 1); // 1번부터 시작
		System.out.println(answer);
	}

	private static void dfs(final Queue<int[]>[] q, final int cur) {
		while (true) {
			while (!q[cur].isEmpty() && visit[q[cur].peek()[1]]) {
				q[cur].poll(); // 이미 체크한 간선 번호는 queue에서 빼줍니다.
			}

			if (q[cur].isEmpty()) { break; }

			int[] p = q[cur].poll(); // { next, id } : cur -> next인 곳과 해당 간선의 번호
			visit[p[1]] = true;
			dfs(q, p[0]); // next dfs
		}
		answer.append(cur + " ");
	}

	private static Queue<int[]>[] getQueues(int n) {
		Queue<int[]>[] q = new Queue[n + 1];
		for (int i = 1; i <= n; i++) {
			q[i] = new LinkedList<>();
		}
		return q;
	}
}
