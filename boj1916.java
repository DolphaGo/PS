import java.util.*;
import java.io.*;

public class boj1916 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());

		ArrayList<int[]> list[]=new ArrayList[n+1];
		long[][] map = new long[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			list[i]=new ArrayList<>();
			Arrays.fill(map[i],Long.MAX_VALUE/2);
		}
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new int[] {b,c});
		}
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		PriorityQueue<int[]> q = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		q.add(new int[] { a, 0 });
		int answer=0;
		while (!q.isEmpty()) {
			int[] p = q.poll();
			int cur=p[0];
			if(cur==b) {
				answer=p[1];
				break;
			}
			for(int i=0;i<list[p[0]].size();i++) {
				int[] l=list[p[0]].get(i);
				int next=l[0];
				if(map[cur][next]>p[1]+l[1]) {
					map[cur][next]=p[1]+l[1];
					q.add(new int[] {next,p[1]+l[1]});
				}
			}
		}
		System.out.println(answer);
	}
}