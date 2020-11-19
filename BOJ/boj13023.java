import java.util.*;
import java.io.*;

public class boj13023 {
	static ArrayList<Integer>list[];
	static int n;
	static boolean flag;
	static boolean visit[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		list=new ArrayList[n];
		visit=new boolean[n];
		for(int i=0;i<n;i++) list[i]=new ArrayList<Integer>();
		
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		//a-b,b-c,c-d,d-e의 친구 관계가 있는지 확인
		//즉 5명이 각각 하나씩 연결되어 있는지 확인하기
		//DFS가 좋겠다.
		flag=false;
		for(int i=0;i<n;i++) {
			visit[i]=true;
			dfs(i,1);
			if(flag) {
				System.out.println(1);
				return;
			}
			visit[i]=false;
		}
		System.out.println(0);
	}
	static void dfs(int v,int cnt) {
		if(cnt==5) {
			flag=true;
			return;
		}
		for(int val:list[v]) {
			if(visit[val]) continue;
			visit[val]=true;
			dfs(val,cnt+1);
			visit[val]=false;
		}
	}
}