import java.util.*;
import java.io.*;

public class boj1240 {
	static int n,m,answer,b;
	static ArrayList<int[]> list[];
	static boolean visit[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		list=new ArrayList[n+1];
		for(int i=1;i<=n;i++) list[i]=new ArrayList<>();
		for(int i=0;i<n-1;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			list[a].add(new int[]{b,c});
			list[b].add(new int[]{a,c});
		}
		visit=new boolean[n+1];
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			answer=0;
			visit[a]=true;
			go(a,0);
			visit[a]=false;
			sb.append(answer+"\n");
		}
		System.out.print(sb);
	}
	static void go(int a,int cost) {
		if(a==b) {
			answer=cost;
			return;
		}
		for(int i=0;i<list[a].size();i++) {
			int[] next=list[a].get(i);
			if(!visit[next[0]]) {
				visit[next[0]]=true;
				go(next[0],cost+next[1]);
				visit[next[0]]=false;
			}
		}
	}
}