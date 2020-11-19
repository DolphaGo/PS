import java.util.*;
import java.io.*;

public class boj1389 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		ArrayList<Integer> list[]=new ArrayList[n+1];
		for(int i=1;i<=n;i++) list[i]=new ArrayList<Integer>();
		int m=Integer.parseInt(st.nextToken());
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		int answer=0;
		int min=Integer.MAX_VALUE;
		Queue<int[]> q=new LinkedList<int[]>();
		boolean[] visit=new boolean[n+1];
		for(int i=1;i<=n;i++) {
			visit[i]=true;
			int res=0;
			q.add(new int[] {i,0});
			while(!q.isEmpty()) {
				int[] p=q.poll();
				int cur=p[0];
				for(int j=0;j<list[cur].size();j++) {
					int next=list[cur].get(j);
					if(!visit[next]) {
						visit[next]=true;
						res+=p[1]+1;
						q.add(new int[] {next,p[1]+1});
					}
				}
			}
			if(res<min) {
				min=res;
				answer=i;
			}
			//다음 사용을 위해 초기화
			Arrays.fill(visit, false);
		}
		System.out.println(answer);
	}
}
