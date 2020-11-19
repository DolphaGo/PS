import java.util.*;
import java.io.*;

public class Main {
	static long arr[];
	static long tree[];
	static long init(int node,int start,int end) {
		//leaf node
		if(start==end) {
			return tree[node]=arr[start];
		}
		return tree[node]=init(node*2,start,(start+end)/2)
				+init(node*2+1,(start+end)/2+1,end);
	}
	
	static long sum(int node,int start,int end,int left,int right) {
		if(start>right || end<left) return 0;
		
		if(left<=start&&end<=right) {
			return tree[node];
		}
		
		return sum(node*2,start,(start+end)/2,left,right)
				+sum(node*2+1,(start+end)/2+1,end,left,right);
		
	}
	
	static void update(int node,int start,int end,int idx,long diff) {
		if(idx<start || idx>end) return;
		
		tree[node]+=diff;
		if(start!=end) {
			update(node*2,start,(start+end)/2,idx,diff);
			update(node*2+1,(start+end)/2+1,end,idx,diff);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		arr=new long[n+1];
		
		int h=(int)Math.ceil(Math.log(n)/Math.log(2));
		tree=new long[1<<h+1];
		for(int i=1;i<=n;i++) {
			arr[i]=Long.parseLong(br.readLine());
		}
		init(1,1,n);
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<m+k;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			if(a==1) {
				int b=Integer.parseInt(st.nextToken());
				long c=Long.parseLong(st.nextToken());
				long diff=c-arr[b];
				arr[b]=c;
				update(1,1,n,b,diff);
			}else {
				int b=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				sb.append(sum(1,1,n,b,c)+"\n");
			}
		}
		System.out.println(sb);
	}
}