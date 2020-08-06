import java.io.*;
import java.util.*;

public class Main {
	static long[] arr,tree,lazy;
	static long init(int node,int start,int end){
		if(start==end){
			return tree[node]=arr[start];
		}
		int m=(start+end)>>1;
		return tree[node]=init(node*2,start,m)+init(node*2+1,m+1,end);
	}
	static void LazyUpdate(int node,int start,int end){
		if(lazy[node]!=0){
			tree[node]+=(end-start+1)*lazy[node];
			if(start!=end){
				lazy[node*2]+=lazy[node];
				lazy[node*2+1]+=lazy[node];
			}
			lazy[node]=0;
		}
	}
	static void add(int node,int start,int end,int left,int right,long k){
		LazyUpdate(node,start,end);
		if(end<left || start > right) return;
		if(left<=start&&end<=right){
			tree[node]+=(end-start+1)*k;
			if(start!=end){
				lazy[node*2]+=k;
				lazy[node*2+1]+=k;
			}
			return;
		}
		int m=(start+end)>>1;
		add(node*2,start,m,left,right,k);
		add(node*2+1,m+1,end,left,right,k);
	}
	static long query(int node,int start,int end,int x){
		LazyUpdate(node,start,end);
		if(x<start || x>end) return 0;
		if(start==end) return tree[node];
		else{
			int m=(start+end)>>1;
			return query(node*2,start,m,x)+query(node*2+1,m+1,end,x);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n=Integer.parseInt(br.readLine());
		arr=new long[n+1];
		int h=(int)Math.ceil(Math.log(n)/Math.log(2));
		tree=new long[1<<h+1];
		lazy=new long[1<<h+1];

		st=new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++){
			arr[i]=Long.parseLong(st.nextToken());
		}

		init(1,1,n);

		StringBuilder sb=new StringBuilder();

		int m=Integer.parseInt(br.readLine());
		for(int q=0;q<m;q++){
			st=new StringTokenizer(br.readLine());
			int Q=Integer.parseInt(st.nextToken());

			if(Q==1){
				int i=Integer.parseInt(st.nextToken());
				int j=Integer.parseInt(st.nextToken());
				long k=Long.parseLong(st.nextToken());
				add(1,1,n,i,j,k);
			}else{
				int x=Integer.parseInt(st.nextToken());
				sb.append(query(1,1,n,x)+"\n");
			}
		}
		System.out.print(sb);
	}
}
