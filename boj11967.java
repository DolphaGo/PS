import java.util.*;
import java.io.*;

public class boj11967 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ArrayList<int[]> map[][] = new ArrayList[n][n];
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				map[y][x] = new ArrayList<int[]>();
			}
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[y][x].add(new int[] { b, a });
		}
		boolean[][] light = new boolean[n][n];
		boolean[][] visit = new boolean[n][n];
		light[0][0]= true; //불을 켠 위치
		visit[0][0]= true; //방문 가능한 위치
		int answer = 1;// 처음 위치는 불 밝힐 수 있다 그랬음.
		Queue<int[]> q=new LinkedList<int[]>();
		q.add(new int[] {0,0});
		int dy[]= {-1,1,0,0};
		int dx[]= {0,0,-1,1};
		while(!q.isEmpty()) {
			int[] p=q.poll();
			int y=p[0]; int x=p[1];
			for(int[] pp:map[p[0]][p[1]]) {
				int ny=pp[0];
				int nx=pp[1];
				if(!light[ny][nx]) {
					light[ny][nx]=true; //불을 킴.
					++answer;
					for(int j=0;j<4;j++) {
						int nny=ny+dy[j];
						int nnx=nx+dx[j];
						if(nny>=0&&nnx>=0&&nny<n&&nnx<n&&visit[nny][nnx]) {
							//불 킨 곳주변에 갈 수 있다면
							visit[ny][nx]=true;//갈 수 있습니다.
							q.add(new int[] {ny,nx});
						}
					}
				}
			}
			for(int i=0;i<4;i++) {
				int ny=y+dy[i];
				int nx=x+dx[i];
				if(ny>=0&&nx>=0&&ny<n&&nx<n&&light[ny][nx]&&!visit[ny][nx]) {
					//갈 수 있는 범위 내에서
					visit[ny][nx]=true; //불이 켜져있는데 방문한적 없을 때
					q.add(new int[] {ny,nx});
				}
			}
		}
		System.out.println(answer);
	}
}
