import java.util.*;
import java.io.*;

public class boj10999 {
	static long[] tree, arr,lazy;
	static int n;

	static long init(int node, int start, int end) {
		if (start == end) {
			return tree[node] = arr[start];
		} else {
			return tree[node] = init(node * 2, start, (start + end) / 2)
					+ init(node * 2 + 1, (start + end) / 2 + 1, end);
		}
	}

	static long sum(int node, int start, int end, int left, int right) {
		//현재 노드 Lazy update
		LazyUpdate(node,start,end);
		if (start > right || end < left)
			return 0;
		if (left <= start && end <= right)
			return tree[node];
		return sum(node * 2, start, (start + end) / 2, left, right)
				+ sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
	}

	static void LazyUpdate(int node,int start,int end) {
		if(lazy[node]!=0) {
			tree[node] += (end-start+1)*lazy[node];
			
			//leaf-node가 아니라면 lazy넘기자
			if(start != end) {
				lazy[node*2] += lazy[node];
				lazy[node*2+1] += lazy[node];
			}
			//현재 노드의 lazy는 비워준다.
			lazy[node]=0;
		}
	}
	
	static void update(int node, int start, int end, int left, int right, long d) {
		//현재 노드 Lazy 업데이트
		LazyUpdate(node,start,end);
		// 전혀 포함이 되지 않을 때
		if (end < left || start > right)
			return;
		// 노드의 범위가 구간에 완전히 포함될 때(리프노드 포함)
		if(left<=start && end<=right) {
			tree[node] += (end-start+1)*d;
			//리프노드가 아니라면 Lazy 넘겨주기
			if(start != end) {
				lazy[node*2] +=d;
				lazy[node*2+1]+=d;
			}
			return;
		}
		update(node*2,start,(start+end)/2,left,right,d);
		update(node*2+1,(start+end)/2+1,end,left,right,d);
		tree[node]=tree[node*2]+tree[node*2+1];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int h = (int) Math.ceil(Math.log(n) / Math.log(2));
		arr = new long[n + 1];
		tree = new long[1 << (h + 1)];
		lazy = new long[1 << (h + 1)];

		for (int i = 1; i <= n; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		init(1, 1, n);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if (a == 1) {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				long d = Long.parseLong(st.nextToken());
				update(1, 1, n, b, c, d);
			} else {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				sb.append(sum(1, 1, n, b, c) + "\n");
			}
		}
		System.out.print(sb);
	}
}