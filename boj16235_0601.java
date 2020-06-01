import java.io.*;
import java.util.*;

class boj16235_0601 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[][] A = new int[n][n];
		int[][] map = new int[n][n];
		PriorityQueue<Integer> tree[][] = new PriorityQueue[n][n];
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				A[y][x] = Integer.parseInt(st.nextToken());
				map[y][x] = 5;
				tree[y][x] = new PriorityQueue<>();
			}
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			tree[y][x].add(z);
		}

		Queue<Integer> live = new LinkedList<Integer>();
		for (int i = 0; i < k; i++) {
			for (int y = 0; y < n; y++) {
				for (int x = 0; x < n; x++) {
					int die = 0;
					// 봄
					while (!tree[y][x].isEmpty()) {
						int p = tree[y][x].poll();
						if (map[y][x] >= p) {
							map[y][x] -= p;
							live.add(p + 1);
						} else
							die += p / 2;
					}
					// 살은 것들 처리
					while (!live.isEmpty()) {
						tree[y][x].add(live.poll());
					}
					// 여름
					map[y][x] += die;

					// 겨울
					map[y][x] += A[y][x];
				}
			}

			// 가을
			for (int y = 0; y < n; y++) {
				for (int x = 0; x < n; x++) {
					if (tree[y][x].size() > 0) {
						Iterator<Integer> it = tree[y][x].iterator();
						while (it.hasNext()) {
							int age = it.next();
							if (age % 5 == 0) {
								for (int yy = y - 1; yy <= y + 1; yy++) {
									for (int xx = x - 1; xx <= x + 1; xx++) {
										if (yy == y && xx == x)
											continue;
										if (yy >= 0 && xx >= 0 && yy < n && xx < n) {
											tree[yy][xx].add(1);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		int answer = 0;
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				answer += tree[y][x].size();
			}
		}
		System.out.println(answer);

	}
}
