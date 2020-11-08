import java.io.*;
import java.util.*;

public class Main {
	static int[] tree,lazy;
	static void lazyUpdate(int node,int start,int end){
		if(lazy[node]!=0){
			tree[node]^=lazy[node];
			if(start!=end){
				lazy[node<<1]^=lazy[node];
				lazy[node<<1|1]^=lazy[node];
			}
			lazy[node]=0;
		}
	}
	static void update(int node, int start, int end, int left, int right, int diff){
		lazyUpdate(node,start,end);
		if(end<left || right<start) return;
		if(left<=start&&end<=right){
			tree[node]^=diff;
			if(start!=end){
				lazy[node<<1]^=diff;
				lazy[node<<1|1]^=diff;
			}
			return;
		}
		int m=(start+end)>>1;
		update(node<<1,start,m,left,right,diff);
		update(node<<1|1,m+1,end,left,right,diff);
	}

	static int query(int node,int start,int end,int idx){
		lazyUpdate(node,start,end);
		if(idx>end || idx<start) return 0;
		if(start==end){
			return tree[node];
		}
		int m=(start+end)>>1;
		return query(node<<1,start,m,idx)+query(node<<1|1,m+1,end,idx);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n=Integer.parseInt(br.readLine());
		int h=(int)Math.ceil(Math.log(n)/Math.log(2));
		tree=new int[1<<h+1];
		lazy=new int[1<<h+1];

		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			int val=Integer.parseInt(st.nextToken());
			update(1,0,n-1,i,i,val);
		}

		StringBuilder sb=new StringBuilder();
		int m=Integer.parseInt(br.readLine());
		for(int i=0;i<m;i++){
			st=new StringTokenizer(br.readLine());
			int t=Integer.parseInt(st.nextToken());
			int a=Integer.parseInt(st.nextToken());
			if(t==1){
				int b=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				update(1,0,n-1,a,b,c);
			}else{
				sb.append(query(1,0,n-1,a)).append("\n");
			}
		}
		System.out.print(sb);
	}
}