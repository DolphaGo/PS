import java.util.*;
import java.io.*;

public class boj9944 {
	static int answer, n, m, emp;
	static char[][] map;
	static boolean[][] visit;
	static ArrayList<int[]> pos = new ArrayList<int[]>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = 1;
		String s = br.readLine();
			while (true) {
				if(s!=null) st=new StringTokenizer(s);
				else break;
				n = Integer.parseInt(st.nextToken());
				m = Integer.parseInt(st.nextToken());
				map = new char[n][m];
				visit = new boolean[n][m];
				emp = 0;
				for (int y = 0; y < n; y++) {
					map[y] = br.readLine().toCharArray();
					for (int x = 0; x < m; x++) {
						if (map[y][x] == '.') {
							pos.add(new int[] { y, x });
							++emp;
						}
					}
				}
				answer = Integer.MAX_VALUE;
				for (int[] p : pos) {
					int cury = p[0];
					int curx = p[1];
					visit[cury][curx] = true;
					go(cury, curx, 0, 1);
					visit[cury][curx] = false;
				}
				if (answer == Integer.MAX_VALUE)
					answer = -1;
				System.out.println("Case " + tc + ": " + answer);
				pos.clear();
				++tc;
				if(br.ready()) s=br.readLine();
				else break;
		}
	}

	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, 1, -1 };

	static void go(int cury, int curx, int move, int vcnt) {
		if (vcnt == emp) {
			answer = answer > move ? move : answer;
			return;
		}
		Queue<int[]> rec = new LinkedList<int[]>();
		for (int i = 0; i < 4; i++) {
			int ny = cury + dy[i];
			int nx = curx + dx[i];
			int vcount = 0;
			while (ny >= 0 && nx >= 0 && ny < n && nx < m && map[ny][nx] == '.' && !visit[ny][nx]) {
				visit[ny][nx] = true;
				rec.add(new int[] { ny, nx });
				vcount++;
				ny += dy[i];
				nx += dx[i];
			}
			if (vcount != 0) {
				go(ny - dy[i], nx - dx[i], move + 1, vcnt + vcount);
				while (!rec.isEmpty()) {
					int[] p = rec.poll();
					visit[p[0]][p[1]] = false;
				}
			}
		}

	}
}
