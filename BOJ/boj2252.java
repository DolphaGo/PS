import java.util.*;
import java.io.*;

public class boj2252 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		
		int indegree[]=new int[n+1];
		ArrayList<Integer> list[]=new ArrayList[n+1];
		for(int i=1;i<=n;i++) list[i]=new ArrayList<Integer>();
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			//a b순으로 서야 한다는 것.
			++indegree[b];
			list[a].add(b);
		}
		
		Queue<Integer> q=new LinkedList<Integer>();
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<=n;i++) {
			if(indegree[i]==0) q.add(i);
		}
		while(!q.isEmpty()) {
			int p=q.poll();
			sb.append(p+" ");
			for(int i:list[p]) {
				--indegree[i];
				if(indegree[i]==0) q.add(i);
			}
		}
		System.out.println(sb);
	}
}