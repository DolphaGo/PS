import java.util.*;
import java.io.*;

public class boj11779 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n=Integer.parseInt(br.readLine());
		int m=Integer.parseInt(br.readLine());
		ArrayList<int[]> list[]=new ArrayList[n+1];
		for(int i=1;i<=n;i++) list[i]=new ArrayList<int[]>();
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			list[a].add(new int[] {b,c});
		}
		st=new StringTokenizer(br.readLine());
		int from=Integer.parseInt(st.nextToken());
		int to=Integer.parseInt(st.nextToken());
		int[] dist=new int[n+1];
		int[] rec=new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[from]=0;
		Queue<Integer> q=new LinkedList<>();
		q.add(from);
		while(!q.isEmpty()) {
			int now=q.poll();
			for(int i=0;i<list[now].size();i++) {
				int[] p=list[now].get(i);
				int next=p[0];
				int cost=p[1];
				if(dist[next]>dist[now]+cost) {
					dist[next]=dist[now]+cost;
					rec[next]=now;//next로 가는 최단 경로 직전 위치는 now이다.
					q.add(next);
				}
			}
		}
		System.out.println(dist[to]);
		Stack<Integer> trace=new Stack<Integer>();
		//경로 역추적
		while(to!=0) {
			trace.push(to);
			to=rec[to];
		}
		System.out.println(trace.size());
		StringBuilder sb=new StringBuilder();
		while(!trace.isEmpty()) sb.append(trace.pop()+" ");
		System.out.print(sb);
	}
}
