import java.io.*;
import java.util.*;

public class boj17070_0602 {
	static int n, answer;
	static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		// 0 : 가로, 1: 세로 , 2: 대각선
		answer = 0;
		go(0, 1, 0);
		System.out.println(answer);
	}

	static int dy[] = { 0, 1, 1 };
	static int dx[] = { 1, 0, 1 };
	static int dset[][] = { { 0, 2 }, { 1, 2 }, { 0, 1, 2 } };

	static void go(int y, int x, int dir) {
		if (y == n - 1 && x == n - 1) {
			++answer;
			return;
		}
		for (int i = 0; i < dset[dir].length; i++) {
			int d = dset[dir][i];
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (ny >= 0 && nx >= 0 && ny < n && nx < n && check(ny, nx, d)) {
				go(ny, nx, d);
			}
		}
	}
	static boolean check(int y, int x, int dir) {
		if (dir == 2) {
			if (map[y - 1][x] != 0 || map[y][x] != 0 || map[y][x - 1] != 0)
				return false;
			else
				return true;
		} else
			return map[y][x] == 0 ? true : false;
	}

}