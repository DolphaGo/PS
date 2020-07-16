import java.util.*;
import java.io.*;
public class boj2056{
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
			int num=Integer.parseInt(st.nextToken());
			indegree[i]+=num;
			while(num-->0) {
				int val=Integer.parseInt(st.nextToken());
				list[val].add(i);
			}
		}
		PriorityQueue<int[]> q=new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				return Integer.compare(o1[1],o2[1]);
			}
		});
		for(int i=1;i<=n;i++) {
			if(indegree[i]==0) {
				q.add(new int[] {i,time[i]});
			}
		}
		int ans=0;
		while(!q.isEmpty()) {
			int[] p=q.poll();
			int cur=p[0];
			ans=Math.max(ans,p[1]);
			for(int i=0;i<list[cur].size();i++) {
				int val=list[cur].get(i);
				indegree[val]--;
				if(indegree[val]==0) {
					q.add(new int[] {val,time[val]+p[1]});
				}
			}
		}
		System.out.println(ans);
	}
}