import java.util.*;
import java.io.*;

public class boj10868 {
	static int[] arr,tree;
	static int init(int node,int start,int end) {
		if(start==end) {
			return tree[node]=arr[start];
		}
		int m=(start+end)>>1;
		return tree[node]=Math.min(init(node*2,start,m),init(node*2+1,m+1,end));
	}
	
	static int query(int node,int start,int end,int left,int right) {
		if(end<left||start>right) return Integer.MAX_VALUE;
		if(left<=start&&end<=right) return tree[node];
		
		int m=(start+end)>>1;
		return Math.min(query(node*2,start,m,left,right),query(node*2+1,m+1,end,left,right));
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		
		arr=new int[n+1];
		int h=(int)Math.ceil(Math.log(n)/Math.log(2));
		tree=new int[1<<h+1];
		
		for(int i=1;i<=n;i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		init(1,1,n);
		
		for(int i=1;i<=m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			sb.append(query(1,1,n,a,b)+"\n");
		}
		System.out.print(sb);
	}
}