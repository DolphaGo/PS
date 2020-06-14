import java.util.*;
import java.io.*;

public class boj11724 {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> list[]=new ArrayList[n+1];
		for(int i=1;i<=n;i++) list[i]=new ArrayList<>();
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		Queue<Integer> q=new LinkedList<Integer>();
		boolean visit[]=new boolean[n+1];
		int answer=0;
		for(int i=1;i<=n;i++) {
			if(!visit[i]) {
				++answer;
				visit[i]=true;
				q.add(i);
				while(!q.isEmpty()) {
					int p=q.poll();
					for(int j=0;j<list[p].size();j++) {
						int val=list[p].get(j);
						if(!visit[val]) {
							visit[val]=true;
							q.add(val);
						}
					}
				}
			}
		}
		System.out.println(answer);
	}
}