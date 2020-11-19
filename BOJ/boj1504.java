import java.util.*;
import java.io.*;

public class boj1504 {
	static int n;
	static int arr[][],dist[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		int e=Integer.parseInt(st.nextToken());
		arr=new int[n+1][n+1];
		dist=new int[n+1];
		for(int i=0;i<e;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			arr[a][b]=c;
			arr[b][a]=c;
		}
		st=new StringTokenizer(br.readLine());
		int v1=Integer.parseInt(st.nextToken());
		int v2=Integer.parseInt(st.nextToken());
		
		long answer=0;
		//v1->...->v2 : 양방향이기에 v2->...->v1도 동일함.
		long bet=Dijkstra(v1,v2);
		//1->...->v1->...->v2->...->n
		long res1=Dijkstra(1,v1)+bet+Dijkstra(v2,n);
		//1->...->v2->...->v1->...->n
		long res2=Dijkstra(1,v2)+bet+Dijkstra(v1,n);
		answer=res1>res2?res2:res1;
		System.out.println(answer>=Integer.MAX_VALUE?-1:answer);
	}
	static long Dijkstra(int start,int end) {
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<int[]> q=new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				return Integer.compare(o1[1],o2[1]);
			}
		});
		dist[start]=0;
		q.add(new int[] {start,0});
		while(!q.isEmpty()) {
			int[] p=q.poll();
			int now=p[0];
			int cost=p[1];
			if(now==end) return (long)cost;
			for(int i=1;i<=n;i++) {
				if(arr[now][i]!=0&&dist[i]>dist[now]+arr[now][i]) {
					dist[i]=dist[now]+arr[now][i];
					q.add(new int[] {i,dist[i]});
				}
			}
		}
		return Integer.MAX_VALUE;
	}
}