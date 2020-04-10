import java.util.*;
import java.io.*;

public class boj18870 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		PriorityQueue<int[]> pq=new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			pq.add(new int[] {Integer.parseInt(st.nextToken()),i});
		}
		int ans[]=new int[n];
		int prev=Integer.MIN_VALUE;
		int cnt=0;
		Queue<int[]> q=new LinkedList<int[]>();
		while(!pq.isEmpty()) {
			prev=pq.peek()[0];
			while(pq.size()>0&&pq.peek()[0]==prev) {
				q.add(pq.poll());
			}
			while(!q.isEmpty()) {
				int[] p=q.poll();
				ans[p[1]]=cnt;
			}
			++cnt;
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<n;i++) {
			sb.append(ans[i]+" ");
		}
		System.out.println(sb);
	}
}