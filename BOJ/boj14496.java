import java.util.*;
import java.io.*;

class boj14496{
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int a=Integer.parseInt(st.nextToken());
		int b=Integer.parseInt(st.nextToken());
		
		st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		
		//문제는 a->b로 가는 최단거리(최소비용)
		ArrayList<Integer> list[]=new ArrayList[n+1];
		for(int i=1;i<=n;i++) list[i]=new ArrayList<>();
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			list[x].add(y);
			list[y].add(x);
		}
		PriorityQueue<int[]> q=new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				return Integer.compare(o1[1],o2[1]);
			}
		});
		//Queue<Integer> q=new LinkedList<Integer>();
		q.add(new int[] {a,0});
		int[] dist=new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[a]=0;
		boolean visit[]=new boolean[n+1];
		
		while(!q.isEmpty()) {
			int[] p=q.poll();
			int now=p[0];
			if(visit[now]) continue;
			visit[now]=true;
			if(now==b) {
				System.out.println(p[1]);
				return;
			}
			for(int i=0;i<list[now].size();i++) {
				int next=list[now].get(i);
				if(dist[next]>dist[now]+1) {
					dist[next]=dist[now]+1;
					q.add(new int[] {next,dist[next]});
				}
			}
		}
		System.out.println(-1);
	}
}