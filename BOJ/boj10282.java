import java.util.*;
import java.io.*;

public class boj10282 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int visit[]=new int[n+1];
			Arrays.fill(visit, Integer.MAX_VALUE/2);
			PriorityQueue<int[]> q=new PriorityQueue<>(new Comparator<int[]>() {
				public int compare(int[] o1,int[] o2) {
					return Integer.compare(o1[1], o2[1]);
				}
			});
			visit[c]=0;
			q.add(new int[] {c,0});
			
			ArrayList<int[]> list[]=new ArrayList[n+1];
			for(int i=1;i<=n;i++) list[i]=new ArrayList<int[]>();
			for(int i=0;i<d;i++) {
				st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				int s=Integer.parseInt(st.nextToken());
				list[b].add(new int[] {a,s});
			}
			int last=0;
			int cnt=0;
			while(!q.isEmpty()) {
				int[] p=q.poll();
				int cur=p[0];
				if(visit[cur]<p[1]) continue;
				++cnt;
				last=Math.max(last,p[1]);
				for(int i=0;i<list[cur].size();i++) {
					int[] np=list[cur].get(i);
					int next=np[0];
					if(visit[next]>p[1]+np[1]) {
						visit[next]=p[1]+np[1];
						q.add(new int[] {next,p[1]+np[1]});
					}
				}
			}
			sb.append(cnt+" "+last+"\n");
		}
		System.out.print(sb);
	}
}