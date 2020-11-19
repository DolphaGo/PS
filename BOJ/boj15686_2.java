import java.io.*;
import java.util.*;

public class boj15686_2 {
	static int answer, n, m;
	static int[][] map, selected;
	static ArrayList<int[]> chicken = new ArrayList<int[]>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		selected = new int[m][2];
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				if (map[y][x] == 2) {
					chicken.add(new int[] { y, x });
				}
			}
		}
		answer = Integer.MAX_VALUE;
		go(0, 0);
		System.out.println(answer);
	}

	static void go(int v, int select) {
		if (select == m) {
			int val = getdistance();
			answer = answer > val ? val : answer;
			return;
		}
		if (v == chicken.size())	return;

		int[] cur = chicken.get(v);
		selected[select][0]=cur[0];
		selected[select][1]=cur[1];
		go(v + 1, select + 1);
		go(v + 1, select);
	}

	static int getdistance() {
		int dis = 0;
		boolean[][] v = new boolean[n][n];
		int[] dy = { -1, 1, 0, 0 };
		int[] dx = { 0, 0, -1, 1 };

		Queue<int[]> q = new LinkedList<int[]>();
		for(int i=0;i<m;i++) {
			q.add(new int[] {selected[i][0],selected[i][1],0});
			v[selected[i][0]][selected[i][1]]=true;
		}
		
		while (!q.isEmpty()) {
			int[] p=q.poll();
			for(int i=0;i<4;i++) {
				int ny=p[0]+dy[i];
				int nx=p[1]+dx[i];
				if(ny>=0&&nx>=0&&ny<n&&nx<n&&!v[ny][nx]) {
					v[ny][nx]=true;
					if(map[ny][nx]==1) dis+=(p[2]+1);
					
					q.add(new int[] {ny,nx,p[2]+1});
				}
			}
		}
		return dis;
	}
}