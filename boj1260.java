import java.util.*;
import java.io.*;

public class boj1260 {
	static int n;
	static ArrayList<Integer> list[];
	static boolean visit[];
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int v=Integer.parseInt(st.nextToken());
		
		visit=new boolean[n+1];
		list=new ArrayList[n+1];
		for(int i=1;i<=n;i++) list[i]=new ArrayList<Integer>();
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		//정점 오름차순으로 정렬
		for(int i=1;i<=n;i++) Collections.sort(list[i]);
		
		sb.append(v+" ");
		visit[v]=true;
		DFS(v);
		
		sb.append("\n"+v+" ");
		BFS(v);
		System.out.print(sb);
	}
	static void DFS(int v) {
		for(int i=0;i<list[v].size();i++) {
			int val=list[v].get(i);
			if(!visit[val]) {
				visit[val]=true;
				sb.append(val+" ");
				DFS(val);
			}
		}
	}
	static void BFS(int v) {
		Queue<Integer> q=new LinkedList<Integer>();
		Arrays.fill(visit, false);
		visit[v]=true;
		q.add(v);
		while(!q.isEmpty()) {
			int p=q.poll();
			for(int i=0;i<list[p].size();i++) {
				int val=list[p].get(i);
				if(!visit[val]) {
					visit[val]=true;
					sb.append(val+" ");
					q.add(val);
				}
			}
		}
	}
}