import java.util.*;
import java.io.*;

public class boj14889_개선버전 {
	static int n,answer;
	static int p[][],team[];
	static boolean visit[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		p = new int[n][n];
		team=new int[n/2];
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				p[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		visit=new boolean[n];
		answer = Integer.MAX_VALUE;
		go(0,0);
		System.out.println(answer);
	}
	static void go(int v,int select) {
		if(v==n) return;
		if(select==n/2) {
			int team2[]=new int[n/2];
			int idx=0;
			for(int i=0;i<n;i++) {
				if(!visit[i]) team2[idx++]=i;
			}
			int A=0;
			int B=0;
			for(int i=0;i<n/2;i++) {
				for(int j=0;j<n/2;j++) {
					 A+=p[team[i]][team[j]];
					 B+=p[team2[i]][team2[j]];
				}
			}
			answer=Math.min(answer,Math.abs(A-B));
			return;
		}
		visit[v]=true;
		team[select]=v;
		go(v+1,select+1);
		visit[v]=false;
		go(v+1,select);
	}
}