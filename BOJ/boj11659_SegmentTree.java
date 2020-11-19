import java.util.*;
import java.io.*;

public class boj11659_SegmentTree {
	static int n;
	static int[] arr, tree;

	static int init(int node, int start, int end) {
		if(start==end) {
			return tree[node]=arr[start];
		}
		return tree[node]=init(node*2,start,(start+end)/2)+init(node*2+1,(start+end)/2+1,end);
	}
	
	static int sum(int node,int start,int end,int left,int right) {
		if(start>right || end<left) return 0;
		
		if(left<=start&&end<=right) {
			return tree[node];
		}
		
		return sum(node*2,start,(start+end)/2,left,right)
				+sum(node*2+1,(start+end)/2+1,end,left,right);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new int[n + 1];
		int h=(int)Math.ceil(Math.log(n)/Math.log(2));
		tree = new int[1<<h+1];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		init(1,1,n);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int res = sum(1,1,n,a,b);
			sb.append(res + "\n");
		}
		System.out.println(sb);
	}
}