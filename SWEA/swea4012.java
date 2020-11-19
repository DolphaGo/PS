import java.io.*;
import java.util.*;

class swea4012 {
	static int S[][],A[],B[];
	static boolean visit[];
	static int answer,n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			n=Integer.parseInt(br.readLine());
			visit=new boolean[n];
			S=new int[n][n];
			for(int y=0;y<n;y++) {
				st=new StringTokenizer(br.readLine());
				for(int x=0;x<n;x++) {
					S[y][x]=Integer.parseInt(st.nextToken());
				}
			}
			A=new int[n/2];
			B=new int[n/2];
			answer=Integer.MAX_VALUE;
			go(0,0);
			sb.append("#" + tc + " " + answer + "\n");
		}
		System.out.print(sb);
	}
	static void go(int v,int select) {
		if(select==n/2) {
			int idx=0;
			for(int i=0;i<n;i++) {
				if(!visit[i]) B[idx++]=i;
			}
			//문제에서 같은 재료는 시너지 0으로 주어짐
			int a=0;
			int b=0;
			for(int i=0;i<n/2;i++) {
				for(int j=0;j<n/2;j++) {
					a+=S[A[i]][A[j]];
					b+=S[B[i]][B[j]];
				}
			}
			int diff=Math.abs(a-b);
			answer=answer>diff?diff:answer;
			return;
		}
		if(v==n) return;
		visit[v]=true;
		A[select]=v;
		go(v+1,select+1);
		visit[v]=false;
		go(v+1,select);
		
	}
}