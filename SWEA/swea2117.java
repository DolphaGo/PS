import java.io.*;
import java.util.*;

class swea2117 {
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int answer = 0;
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int map[][] = new int[n][n];
			for (int y = 0; y < n; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < n; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			Queue<int[]> q = new LinkedList<int[]>();
			for (int y = 0; y < n; y++) {
				for (int x = 0; x < n; x++) {
					boolean visit[][] = new boolean[n][n];
					int arr[]=new int[n+2];
					visit[y][x]=true;
					if(map[y][x]==1) arr[1]++;
					q.add(new int[] {y,x,1});
					while(!q.isEmpty()) {
						int[] p=q.poll();
						for(int i=0;i<4;i++) {
							int ny=p[0]+dy[i]; 
							int nx=p[1]+dx[i];
							if(ny>=0&&nx>=0&&ny<n&&nx<n&&!visit[ny][nx]) {
								visit[ny][nx]=true;
								if(map[ny][nx]==1) {
									arr[p[2]+1]++;
								}
								if(p[2]+1<=n) q.add(new int[] {ny,nx,p[2]+1});
							}
						}
					}
					for(int i=1;i<=n+1;i++) {
						arr[i]+=arr[i-1];
						int cost=(i*i)+(i-1)*(i-1);
						int money=arr[i]*m;
						int res=money-cost;
						if(res>=0&&answer<arr[i]) {
							answer=arr[i];
						}
					}
				}
			}
			sb.append("#" + tc + " " + answer + "\n");
		}
		System.out.print(sb);
	}
}