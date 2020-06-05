import java.io.*;
import java.util.*;

class swea2115 {
	static int n,m,c,answer;
	static int[][] map;
	static boolean[][] visit;
	static int[][] user;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer=0;
			st = new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			c=Integer.parseInt(st.nextToken());
			map=new int[n][n];
			visit=new boolean[n][n];
			for(int y=0;y<n;y++) {
				st=new StringTokenizer(br.readLine());
				for(int x=0;x<n;x++) {
					map[y][x]=Integer.parseInt(st.nextToken());
				}
			}
			user=new int[2][m];
			selectm(0,0,0);
			sb.append("#" + tc + " " + answer + "\n");
		}
		System.out.print(sb);
	}
	static void selectm(int y,int x,int v) {
		if(v==2) {
			int res=0;
			for(int i=0;i<2;i++) res+=selectc(user[i]);
			answer=answer<res?res:answer;
			return;
		}
		if(y>=n) return;
		
		boolean flag=true;
		for(int xx=x;xx<x+m;xx++) {
			if(xx>=n||visit[y][xx]) {
				flag=false;
				break;
			}
		}
		if(flag) {
			for(int xx=x;xx<x+m;xx++) {
				user[v][xx-x]=map[y][xx];
				visit[y][xx]=true;
			}
			selectm(y,x+m,v+1);
			for(int xx=x;xx<x+m;xx++) visit[y][xx]=false;
		}
		if(x+1<n) selectm(y,x+1,v);
		else selectm(y+1,0,v);
	}
	//arr내에서 c를 넘지 않는 만큼 고르기, 그 선택된 집합들 중 최대 비용구하기
	static int selectc(int[] arr) {
		int res=0;
		for(int i=0;i<(1<<m);i++) {
			int w=0;
			int ww=0;
			for(int j=0;j<m;j++) {
				if((i&(1<<j))>0) {
					w+=arr[j];
					ww+=(arr[j]*arr[j]);
				}
			}
			if(w<=c) res=res<ww?ww:res;
		}
		return res;
	}
}