import java.util.*;
import java.io.*;

public class boj2357 {
	static int[] arr,Mintree,Maxtree;

	static int min_init(int node, int start, int end) {
		if (start == end) {
			return Mintree[node] = arr[start];
		}
		int mid = (start + end) >> 1;
		return Mintree[node] = Math.min(min_init(node * 2, start, mid), min_init(node * 2 + 1, mid + 1, end));
	}

	static int max_init(int node, int start, int end) {
		if (start == end) {
			return Maxtree[node] = arr[start];
		}
		int mid = (start + end) >> 1;
		return Maxtree[node] = Math.max(max_init(node * 2, start, mid), max_init(node * 2 + 1, mid + 1, end));
	}

	static int min_query(int node, int start, int end, int left, int right) {
		if(right<start||end<left) return Integer.MAX_VALUE;
		if(left<=start&&end<=right) {
			return Mintree[node];
		}
		int mid = (start + end) >> 1;
		return Math.min(min_query(node*2,start,mid,left,right),min_query(node*2+1,mid+1,end,left,right));
	}

	static int max_query(int node, int start, int end, int left, int right) {
		if(right<start||end<left) return Integer.MIN_VALUE;
		if(left<=start&&end<=right) {
			return Maxtree[node];
		}
		int mid = (start + end) >> 1;
		return Math.max(max_query(node*2,start,mid,left,right),max_query(node*2+1,mid+1,end,left,right));
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int h = (int) Math.ceil(Math.log(n) / Math.log(2));
		Mintree = new int[1 << (h + 1)];
		Maxtree = new int[1 << (h + 1)];
		arr = new int[n + 1];
		for (int i = 1; i <= n; i++)
			arr[i] = Integer.parseInt(br.readLine());
		min_init(1, 1, n);
		max_init(1, 1, n);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			sb.append(min_query(1, 1, n, l, r) + " " + max_query(1, 1, n, l, r) + "\n");
		}
		System.out.print(sb);
	}
}
