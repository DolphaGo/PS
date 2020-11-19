import java.io.*;
import java.util.*;

public class boj17140_0601 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// R연산 : 모든 행에 대해서 정렬 수행, 행의 개수>=열의 개수일 때
		// C연산 : 모든 열에 대해서 정렬 수행, 행의 개수<열의 개수일 때

		int map[][] = new int[100][100];

		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());

		for (int y = 0; y < 3; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < 3; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		int cmax = 3;// 행의 개수 |
		int rmax = 3;// 열의 개수 ㅡ

		int t = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1])
					return Integer.compare(o1[0], o2[0]);
				return Integer.compare(o1[1], o2[1]);
			}
		});
		while (true) {
			if (map[r][c] == k)
				break;
			++t;
			if(t>100) {
				t=-1;
				break;
			}
			if (cmax >= rmax) {
				// R연산
				for (int y = 0; y < cmax; y++) {
					int[] digit = new int[101];
					for (int x = 0; x < rmax; x++) {
						digit[map[y][x]]++;
						map[y][x] = 0;
					}
					for (int i = 1; i <= 100; i++) {
						if (digit[i] != 0)
							pq.add(new int[] { i, digit[i] });
					}
					int len = pq.size() * 2;
					rmax = rmax < len ? len : rmax;
					int idx = 0;
					while (!pq.isEmpty()) {
						int[] p = pq.poll();
						map[y][idx++] = p[0];
						map[y][idx++] = p[1];
						if(idx==100) {
							pq.clear();
							break;
						}
					}
				}

			} else {
				// C연산
				for (int x = 0; x < rmax; x++) {
					int[] digit = new int[101];
					for (int y = 0; y < cmax; y++) {
						digit[map[y][x]]++;
						map[y][x] = 0;
					}
					for (int i = 1; i <= 100; i++) {
						if (digit[i] != 0)
							pq.add(new int[] { i, digit[i] });
					}
					int len = pq.size() * 2;
					cmax = cmax < len ? len : cmax;
					int idx = 0;
					while (!pq.isEmpty()) {
						int[] p = pq.poll();
						map[idx++][x] = p[0];
						map[idx++][x] = p[1];
						if(idx==100) {
							pq.clear();
							break;
						}
					}
				}
			}
		}
		System.out.println(t);
	}
}