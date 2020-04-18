import java.util.*;
import java.io.*;

public class boj1005 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());
			
			int indegree[]=new int[n+1];
			int delay[]=new int[n+1];
			int max[]=new int[n+1];
			
			st=new StringTokenizer(br.readLine());
			ArrayList<Integer> list[]=new ArrayList[n+1];
			for(int i=1;i<=n;i++) {
				list[i]=new ArrayList<Integer>();
				delay[i]=Integer.parseInt(st.nextToken());
			}
			for(int i=0;i<k;i++) {
				st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				//a를 지은다음 y가 가능, a->b 순
				indegree[b]++;
				list[a].add(b);
			}
			int W=Integer.parseInt(br.readLine());
			
			Queue<int[]> q=new LinkedList<>();
			
			for(int i=1;i<=n;i++) {
				if(indegree[i]==0) q.add(new int[] {i,0});
			}
			int ans=0;
			while(!q.isEmpty()) {
				int[] p=q.poll();
				p[1]+=delay[p[0]];
				if(p[0]==W) {
					ans=p[1];
					break;
				}
				for(int conn:list[p[0]]) {
					--indegree[conn];
					max[conn]=Math.max(max[conn],p[1]);
					if(indegree[conn]==0) q.add(new int[] {conn,max[conn]});
				}
			}
			
			sb.append(ans+"\n");
		}
		System.out.println(sb);
	}
}