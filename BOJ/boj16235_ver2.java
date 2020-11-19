import java.io.*;
import java.util.*;

public class boj16235_ver2 {
	static int n, m, k, add[][], map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		add = new int[n][n];
		map = new int[n][n];
		for (int y = 0; y < n; y++)
			Arrays.fill(map[y], 5);
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				add[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		PriorityQueue<Integer> trees[][] = new PriorityQueue[n][n];

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				trees[y][x] = new PriorityQueue<Integer>();
			}
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			trees[y][x].add(age);
		}
		Queue<Integer> live = new LinkedList<Integer>();
		int dead;
		for (int time = 1; time <= k; time++) {
			// 봄 여름,겨울 한번에 처리 가능
			for (int y = 0; y < n; y++) {
				for (int x = 0; x < n; x++) {
					if (trees[y][x].size() != 0) {
						// 봄
						dead = 0;
						while (!trees[y][x].isEmpty()) {
							int p = trees[y][x].poll();
							if (map[y][x] >= p) {
								map[y][x] -= p;
								live.add(p + 1);
							} else
								dead += p / 2;
						}
						// 산 것들 처리
						while (!live.isEmpty()) {
							int liveTree = live.poll();
							trees[y][x].add(liveTree);
						}
						// 여름
						map[y][x] += dead;
					}
					// 겨울
					map[y][x] += add[y][x];
				}
			}

			for (int y = 0; y < n; y++) {
				for (int x = 0; x < n; x++) {
					if (trees[y][x].size() == 0)
						continue;
					for (int age : trees[y][x]) {
						if (age % 5 == 0) {
							for (int yy = y - 1; yy <= y + 1; yy++) {
								for (int xx = x - 1; xx <= x + 1; xx++) {
									if (yy == y && xx == x)
										continue;
									if (yy >= n || xx >= n || yy < 0 || xx < 0)
										continue;
									trees[yy][xx].add(1);
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
				answer += trees[y][x].size();
			}
		}
		System.out.println(answer);
	}
}