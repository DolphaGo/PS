import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int indegree[]=new int[n+1];
		ArrayList<Integer> list[]=new ArrayList[n+1];
		for(int i=1;i<=n;i++) list[i]=new ArrayList<Integer>();
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			//a->b 가 좋다. 조건은 가능한한 쉬운 문제부터. 따라서 위상정렬
			list[a].add(b);
			indegree[b]++;
		}
		PriorityQueue<Integer> q=new PriorityQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1,Integer o2) {
				if(indegree[o1]==indegree[o2]) return Integer.compare(o1, o2);
				return Integer.compare(indegree[o1], indegree[o2]);
			}
		});
		for(int i=1;i<=n;i++) {
			if(indegree[i]==0) q.add(i);
		}
		StringBuilder sb=new StringBuilder();
		while(!q.isEmpty()) {
			int p=q.poll();
			if(indegree[p]==0) sb.append(p+" ");
			for(int val:list[p]) {
				--indegree[val];
				if(indegree[val]==0) q.add(val);
			}
		}
		System.out.println(sb.toString());
	}
}