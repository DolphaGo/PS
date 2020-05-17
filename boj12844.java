import java.util.*;
import java.io.*;

public class boj12844 {
	static int n;
	static long arr[],tree[],lazy[];
	static long init(int node,int start,int end) {
		if(start==end) {
			return tree[node]=arr[start];
		}
		int m=(start+end)>>1;
		return tree[node]=init(node*2,start,m)^init(node*2+1,m+1,end);
	}
	static void LazyUpdate(int node,int start,int end) {
		if(lazy[node]!=0) {
			int len=(end-start+1)%2;
			if(len==1) tree[node]^=lazy[node];
			if(start!=end) {
				lazy[node*2]^=lazy[node];
				lazy[node*2+1]^=lazy[node];
			}
			lazy[node]=0;
		}
	}
	static void update(int node,int start,int end,int left,int right,int k) {
		LazyUpdate(node,start,end);
		if(start>right||end<left) return;
		if(left<=start&&end<=right) {
			int len=(end-start+1)%2;
			if(len==1) tree[node]^=k;
			if(start!=end) {
				lazy[node*2]^=k;
				lazy[node*2+1]^=k;
			}
			return;
		}
		int m=(start+end)>>1;
		update(node*2,start,m,left,right,k);
		update(node*2+1,m+1,end,left,right,k);
		tree[node]=tree[node*2]^tree[node*2+1];
	}
	
	static long query(int node,int start,int end,int left ,int right) {
		LazyUpdate(node,start,end);
		if(start>right||end<left) return 0;
		if(left<=start&&end<=right) {
			return tree[node];
		}
		int m=(start+end)>>1;
		return query(node*2,start,m,left,right)^query(node*2+1,m+1,end,left,right);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		arr=new long[n];
		int h=(int)Math.ceil(Math.log(n)/Math.log(2));
		tree=new long[1<<h+1];
		lazy=new long[1<<h+1];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) arr[i]=Long.parseLong(st.nextToken());
		
		init(1,0,n-1);
		int m=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int q=Integer.parseInt(st.nextToken());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			if(q==1) {
				int k=Integer.parseInt(st.nextToken());
				update(1,0,n-1,a,b,k);
			}else {
				sb.append(query(1,0,n-1,a,b)+"\n");
			}
		}
		System.out.print(sb);
	}
}