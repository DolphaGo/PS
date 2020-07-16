import java.util.*;
import java.io.*;
public class boj1516{
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n=Integer.parseInt(br.readLine());
		ArrayList<Integer>list[]=new ArrayList[n+1];
		for(int i=1;i<=n;i++) list[i]=new ArrayList<>();
		int[] indegree=new int[n+1];
		int[] time=new int[n+1];
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			time[i]=Integer.parseInt(st.nextToken());
			int prev=Integer.parseInt(st.nextToken());
			while(prev!=-1) {
				indegree[i]++;
				list[prev].add(i);
				prev=Integer.parseInt(st.nextToken());
			}
		}
		PriorityQueue<int[]> q=new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				return Integer.compare(o1[1],o2[1]);
			}
		});
		int ans[]=new int[n+1];
		for(int i=1;i<=n;i++) {
			if(indegree[i]==0) {
				q.add(new int[] {i,time[i]});
				ans[i]=time[i];
			}
		}
		
		while(!q.isEmpty()) {
			int[] p=q.poll();
			int cur=p[0];
			for(int i=0;i<list[cur].size();i++) {
				int val=list[cur].get(i);
				indegree[val]--;
				if(indegree[val]==0) {
					q.add(new int[] {val,time[val]+p[1]});
					ans[val]=time[val]+p[1];
				}
			}
		}
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<=n;i++) sb.append(ans[i]+"\n");
		System.out.print(sb);
	}
}