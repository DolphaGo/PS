import java.util.*;
import java.io.*;

public class boj4485 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		PriorityQueue<int[]> q=new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				return Integer.compare(o1[2],o2[2]);
			}
		});
		int dy[]= {-1,1,0,0};
		int dx[]= {0,0,-1,1};
		int tc=0;
		while(true) {
			int n=Integer.parseInt(br.readLine());
			if(n==0) break;
			int map[][]=new int[n][n];
			for(int y=0;y<n;y++) {
				st=new StringTokenizer(br.readLine());
				for(int x=0;x<n;x++) {
					map[y][x]=Integer.parseInt(st.nextToken());
				}
			}
			int answer=0;
			boolean visit[][]=new boolean[n][n];
			visit[0][0]=true;
			q.add(new int[] {0,0,map[0][0]});
			while(!q.isEmpty()) {
				int[] p=q.poll();
				if(p[0]==n-1&&p[1]==n-1) {
					answer=p[2];
					q.clear();
					break;
				}
				for(int i=0;i<4;i++) {
					int ny=p[0]+dy[i];
					int nx=p[1]+dx[i];
					if(ny>=0&&nx>=0&&ny<n&&nx<n&&!visit[ny][nx]) {
						visit[ny][nx]=true;
						q.add(new int[] {ny,nx,p[2]+map[ny][nx]});
					}
				}
			}
			sb.append("Problem "+ ++tc+": "+answer+"\n");
		}
		System.out.print(sb);
	}
}