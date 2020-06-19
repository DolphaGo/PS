import java.util.*;
import java.io.*;

public class boj10282_0620_ver2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			ArrayList<int[]> list[]=new ArrayList[n+1];
			for(int i=1;i<=n;i++) list[i]=new ArrayList<>();
			for(int i=0;i<d;i++) {
				st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				int s=Integer.parseInt(st.nextToken());
				list[b].add(new int[] {a,s});
			}
			
			
			PriorityQueue<int[]> q=new PriorityQueue<int[]>(new Comparator<int[]>() {
				public int compare(int[] o1,int[] o2) {
					return Integer.compare(o1[1],o2[1]);
				}
			});
			int[] dist=new int[n+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[c]=0;
			q.add(new int[] {c,0});
			while(!q.isEmpty()) {
				int[] p=q.poll();
				int now=p[0];
				//주석하든 안하든 상관X 완탐이라서 이건
				//if(dist[now]<p[1]) continue;
				
				for(int j=0;j<list[now].size();j++) {
					int[] np=list[now].get(j);
					int next=np[0];
					if(dist[next]>dist[now]+np[1]) {
						dist[next]=dist[now]+np[1];
						q.add(new int[] {next,dist[next]});
					}
				}
			}
			
			int ans=0;
			int time=0;
			for(int i=1;i<=n;i++) {
				if(dist[i]!=Integer.MAX_VALUE) {
					time=Math.max(time, dist[i]);
					++ans;
				}
			}
			
			sb.append(ans+" "+time+"\n");
			q.clear();
		}
		System.out.print(sb);
	}}