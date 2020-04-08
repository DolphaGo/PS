import java.util.*;
import java.io.*;

public class boj1922 {
	static int getp(int[] p, int a) {
		if (p[a] == a)
			return a;
		return p[a] = getp(p, p[a]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int p[] = new int[N + 1];
		for (int i = 1; i <= N; i++)
			p[i] = i;

		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.add(new int[] { a, b, c });
		}
		int answer=0;
		while (!pq.isEmpty()) {
			int[] poll = pq.poll();
			int a = poll[0];
			int b = poll[1];
			int c = poll[2];

			a = getp(p, a);
			b = getp(p, b);

			if (a != b) {
				answer+=c;
				if (a > b) {
					p[a]=b;
				} else {
					p[b]=a;
				}
			}
		}
		System.out.println(answer);
	}
}