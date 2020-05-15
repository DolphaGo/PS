import java.io.*;
import java.util.*;

public class boj1395 {
	static int n;
	static int tree[], lazy[];

	static void LazyUpdate(int node, int start, int end) {
		if (lazy[node] != 0) {
			tree[node] = lazy[node] - tree[node];
			if (start != end) {
				lazy[node * 2] = ((start + end) / 2 - start + 1) - lazy[node*2];
				lazy[node * 2 + 1] = (end - ((start + end) / 2 + 1) + 1)- lazy[node*2+1];
			}
			lazy[node] = 0;
		}
	}

	static void update(int node, int start, int end, int left, int right) {
		LazyUpdate(node, start, end);
		if (start > right || end < left)
			return;
		
		if (left <= start && end <= right) {
			tree[node] = (end - start + 1) - tree[node];
			if (start != end) {
				lazy[node * 2] = ((start + end) / 2 - start + 1) - lazy[node*2];
				lazy[node * 2 + 1] = (end - ((start + end) / 2 + 1) + 1) -lazy[node*2+1];
			}
			return;
		}

		update(node * 2, start, (start + end) / 2, left, right);
		update(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		tree[node]=tree[node*2]+tree[node*2+1];
	}

	static int sum(int node, int start, int end, int left, int right) {
		LazyUpdate(node, start, end);
		if (start > right || end < left)
			return 0;

		if (left <= start && end <= right) {
			return tree[node];
		}

		return sum(node * 2, start, (start + end) / 2, left, right)
				+ sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int h = (int) Math.ceil(Math.log(n) / Math.log(2));
		tree = new int[1 << h + 1];
		lazy = new int[1 << h + 1];

		int m = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			if (o == 0)
				update(1, 1, n, s, t);
			else
				sb.append(sum(1, 1, n, s, t) + "\n");
		}
		System.out.print(sb);
	}
}