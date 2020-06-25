import java.util.*;
import java.io.*;

public class boj10026 {
	static int n;
	static char[][] map;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		for (int y = 0; y < n; y++) {
			map[y] = br.readLine().toCharArray();
		}
		System.out.println(Render(0) + " " + Render(1));
	}

	static int Render(int type) {
		boolean visit[][] = new boolean[n][n];
		Queue<int[]> q = new LinkedList<int[]>();
		int cnt = 0;
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				if (!visit[y][x]) {
					visit[y][x] = true;
					++cnt;
					q.add(new int[] { y, x });
					while (!q.isEmpty()) {
						int[] p = q.poll();
						for (int i = 0; i < 4; i++) {
							int ny = p[0] + dy[i];
							int nx = p[1] + dx[i];
							if (isRange(ny, nx) && Ok(map[p[0]][p[1]],map[ny][nx],type) && !visit[ny][nx]) {
								visit[ny][nx] = true;
								q.add(new int[] { ny, nx });
							}
						}
					}
				}
			}
		}
		return cnt;
	}

	static boolean Ok(int pivot, int next, int type) {
		if(pivot==next) return true;
		else if(type==1&&((pivot=='R'&&next=='G')||(pivot=='G'&&next=='R'))) return true;
		else return false;
	}
	static boolean isRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < n && x < n)
			return true;
		return false;
	}
}