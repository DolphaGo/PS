import java.io.*;
import java.util.*;

public class boj1238 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int x=Integer.parseInt(st.nextToken());
		int cost[][]=new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			Arrays.fill(cost[i], Integer.MAX_VALUE/2);
			cost[i][i]=0;
		}
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int start=Integer.parseInt(st.nextToken());
			int end=Integer.parseInt(st.nextToken());
			int co=Integer.parseInt(st.nextToken());
			cost[start][end]=co;
		}
		
		PriorityQueue<int[]> q=new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				return Integer.compare(o1[1],o2[1]);
			}
		});
		boolean v[]=new boolean[n+1];
		//cost1 : 각각 지점으로부터 x까지의 최단거리
		int[] cost1=new int[n+1];
		Arrays.fill(cost1, Integer.MAX_VALUE/2);
		for(int i=1;i<=n;i++) {
			Arrays.fill(v, false);
			v[i]=true; //최단거리 확정
			for(int j=1;j<=n;j++) {
				if(cost[i][j]!=Integer.MAX_VALUE/2) 
					q.add(new int[] {i,cost[i][j]}); //이건 확정 안났으니까 visit처리 안함
			}
			
			while(!q.isEmpty()) {
				int[] p=q.poll();
				int cur=p[0];
				v[cur]=true;//최단 확정
				if(cur==x) {
					cost1[i]=p[1];
					q.clear();
					break;
				}
				for(int j=1;j<=n;j++) {
					if(v[j]) continue; //이미 확정난 것은 검사 안해도 됨
					if(cost[cur][j]==Integer.MAX_VALUE/2) continue;//못가는 거리면 넘겨도돼
					q.add(new int[] {j,p[1]+cost[cur][j]});
				}
			}
		}
		
		int[] cost2=new int[n+1];
		Arrays.fill(cost2, Integer.MAX_VALUE/2);
		//cost2 : x로부터 각각 지점으로의 최단 거리
		q.add(new int[] {x,0});
		while(!q.isEmpty()) {
			int[] p=q.poll();
			int cur=p[0];
			for(int j=1;j<=n;j++) {
				if(cost2[j]>p[1]+cost[cur][j]) {
					cost2[j]=p[1]+cost[cur][j];
					q.add(new int[] {j,cost2[j]});
				}
			}
		}
		int max=Integer.MIN_VALUE;
		for(int i=1;i<=n;i++) {
			if(i==x) continue;
			int AllofCost=cost1[i]+cost2[i];
			if(max<AllofCost) max=AllofCost;
		}
		System.out.println(max);
	}
}
