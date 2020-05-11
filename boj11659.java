import java.util.*;
import java.io.*;

public class boj11659 {
	static int n;
	static int[] arr, tree;

	static void update(int i, int diff) {
		while (i <= n) {
			tree[i] += diff;
			i += (i & -i);
		}
	}

	static int sum(int a) {
		int sum = 0;
		while (a > 0) {
			sum += tree[a];
			a -= (a & -a);
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new int[n + 1];
		tree = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			update(i, arr[i]);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int res = sum(b) - sum(a - 1);
			sb.append(res + "\n");
		}
		System.out.println(sb);
	}
}