import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 도시의 수
		int M = Integer.parseInt(st.nextToken());// 길의 수
		int K = Integer.parseInt(st.nextToken());// 좀비에게 점령 도시
		int S = Integer.parseInt(st.nextToken());// 위험 도시 범위
		st = new StringTokenizer(br.readLine());
		int p = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		Queue<Integer> zombie_q = new LinkedList<>();
		boolean zombied[] = new boolean[N + 1];
		for (int i = 0; i < K; i++) {
			int zombie = Integer.parseInt(br.readLine());
			zombied[zombie] = true;
			zombie_q.add(zombie);
		}

		ArrayList<Integer> list[] = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			list[i] = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		int money[] = new int[N + 1];
		for(int len=0;len<S;len++) {
			int size = zombie_q.size();
			for (int iter = 0; iter < size; iter++) {
				int zom = zombie_q.poll();
				for (int conn : list[zom]) {
					if (money[conn]==0) {
						money[conn] = q;
						zombie_q.add(conn);
					}
				}
			}
		}

		PriorityQueue<long[]> pq = new PriorityQueue<long[]>(new Comparator<long[]>() {
			public int compare(long[] o1, long[] o2) {
				return Long.compare(o1[1], o2[1]);
			}
		});

		boolean visit[] = new boolean[N + 1];
		pq.add(new long[] { 1, 0 });
		visit[1] = true;

		long answer = 0;
		while (!pq.isEmpty()) {
			long[] poll = pq.poll();
			long now = poll[0];
			if (now == N) {
				answer = poll[1];
				break;
			}
			for (int conn : list[(int) now]) {
				if (!visit[conn] && !zombied[conn]) {
					visit[conn] = true;
					int pay = 0;
					// 숙박을 거쳐야 할 때
					if (conn != N)	pay = money[conn] == 0 ? p : q;
					
					pq.add(new long[] { conn, poll[1] + pay });
				}
			}
		}
		System.out.println(answer);
	}
}