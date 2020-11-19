import java.util.*;
import java.io.*;

public class boj11505 {
	static long[] arr,tree;
	static final int MOD=1000000007;
	static long init(int node,int start,int end) {
		//리프노드인 경우
		if(start==end) {
			return tree[node]=arr[start];
		}
		//리프노드가 아닌 경우 자식노드들의 곱
		int m=(start+end)>>1;
		return tree[node]=(init(node*2,start,m)*init(node*2+1,m+1,end))%MOD;
	}
	
	static long update(int node,int start,int end,int index,long val) {
		//실질적인 업데이트는 index를 포함하는 노드안에서만 이루어진다.
		if(start<=index&&index<=end) {
			//리프노드가 index라면 값을 변경한다.
			if(start==end) return tree[node]=val;
			//리프노드가 아니라면 자식노드를 변경한 뒤의 값을 리턴한다.
			int m=(start+end)>>1;
			return tree[node]=(update(node*2,start,m,index,val)*update(node*2+1,m+1,end,index,val))%MOD;
		}
		//인덱스 범위내에 없다면 노드값 그대로 반환한다.
		return tree[node];
	}
	
	static long query(int node,int start,int end,int left,int right) {
		if(end<left||start>right) return 1;
		if(left<=start&&end<=right) return tree[node];
		
		int m=(start+end)>>1;
		return (query(node*2,start,m,left,right)*query(node*2+1,m+1,end,left,right))%MOD;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		
		int h=(int)Math.ceil(Math.log(n)/Math.log(2));
		arr=new long[n+1];
		tree=new long[1<<h+1];
		
		for(int i=1;i<=n;i++) {
			arr[i]=Long.parseLong(br.readLine());
		}
		init(1,1,n);
		for(int i=1;i<=m+k;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			if(a==1) {//b번째 수를 c로 바꾸고, 구간 업데이트
				long c=Long.parseLong(st.nextToken());
				update(1,1,n,b,c);
			}else {
				int c=Integer.parseInt(st.nextToken());
				sb.append(query(1,1,n,b,c)+"\n");
			}
		}
		System.out.print(sb);
	}
}