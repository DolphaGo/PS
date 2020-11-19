import java.util.*;
import java.io.*;

class boj18352{
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		int x=Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> list[]=new ArrayList[n+1];
		for(int i=1;i<=n;i++) list[i]=new ArrayList<>();
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			list[a].add(b);
		}
		
		PriorityQueue<int[]> pq=new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				return Integer.compare(o1[1],o2[1]);
			}
		});
		pq.add(new int[] {x,0});
		boolean visit[]=new boolean[n+1];
		visit[x]=true;
		PriorityQueue<Integer> ans=new PriorityQueue<Integer>();
		while(!pq.isEmpty()) {
			int[] p=pq.poll();
			int now=p[0];
			int cost=p[1];
			if(cost==k) {
				ans.add(now);
				continue;
			}
			for(int i=0;i<list[now].size();i++) {
				int next=list[now].get(i);
				if(!visit[next]) {
					visit[next]=true;
					pq.add(new int[] {next,cost+1});
				}
			}
		}
		if(ans.size()==0) System.out.println(-1);
		else while(!ans.isEmpty()) {
			System.out.println(ans.poll());
		}
	}
}