import java.util.*;
import java.io.*;

public class boj6549 {
	static int n;
	static int[] tree,arr;
	static int init(int node,int start,int end) {
		if(start==end) {
			return tree[node]=start;
		}
		int m=(start+end)>>1;
		int ldx=init(node<<1,start,m);
		int rdx=init((node<<1)+1,m+1,end);
		return tree[node]=arr[ldx]<arr[rdx]?ldx:rdx;
	}
	//해당 구간에서 최소 높이 인덱스를 가져오는 쿼리
	static int query(int node, int start, int end, int left,int right) {
		if(end<left || right<start) return 0;
		
		if(left<=start && end<=right) return tree[node];
		
		int m= (start + end) >> 1;
		int ldx=query(node<<1, start, m, left, right);
		int rdx = query((node<<1)+1, m+1, end, left, right);
		
		if(ldx == 0) return rdx;
		else if(rdx == 0) return ldx;
		else return arr[ldx] < arr[rdx] ? ldx : rdx;
	}
	static long getmax(int left, int right) {
		//인덱스
		int minIdx = query(1,1,n,left,right);
		
		//현재 구간에서 직사각형 계산
		int width=(right-left+1);
		long val=(long)width * arr[minIdx];
		
		//왼쪽
		if(left<=minIdx-1) {
			val = Math.max(val, getmax(left, minIdx-1));
		}
		//오른쪽
		if(minIdx+1<=right) {
			val = Math.max(val, getmax(minIdx+1, right));
		}
		return val;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		while(true) {
			String s=br.readLine();
			if(s.equals("0")) break;
			st=new StringTokenizer(s);
			n=Integer.parseInt(st.nextToken());
			int h=(int)Math.ceil(Math.log(n)/Math.log(2));
			tree=new int[1<<h+1];
			arr=new int[n+1];
			for(int i=1;i<=n;i++) {
				arr[i]=Integer.parseInt(st.nextToken());
			}
			init(1,1,n);
			sb.append(getmax(1,n)+"\n");
		}
		System.out.print(sb);
	}
}