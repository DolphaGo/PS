import java.io.*;
import java.util.*;

public class boj12100_2048 {
	static int n, answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		answer = 0;
		go(0, map);
		System.out.println(answer);
	}

	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	static void go(int v, int[][] map) {
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				answer = Math.max(map[y][x], answer);
			}
		}
		if (v == 5)
			return;
		int arr[][] = new int[n][n];
		for (int y = 0; y < n; y++)
			arr[y] = map[y].clone();
		for (int i = 0; i < 4; i++) {
			move(arr, i);
			go(v + 1, arr);
			for (int y = 0; y < n; y++)
				arr[y] = map[y].clone();
		}
	}

	static void move(int[][] arr, int d) {
		boolean[][] visit = new boolean[n][n];
		switch (d) {
		case 0:
			for (int y = 1; y < n; y++) {
				for (int x = 0; x < n; x++) {
					int ny = y;
					while (ny >= 1 && arr[ny - 1][x] == 0) {
						arr[ny - 1][x] = arr[ny][x];
						arr[ny][x] = 0;
						--ny;
					}

					if (ny >= 1 && arr[ny - 1][x] == arr[ny][x] && !visit[ny - 1][x]) {
						visit[ny - 1][x] = true;
						arr[ny - 1][x] *= 2;
						arr[ny][x] = 0;
					}
				}
			}
			break;
		case 1:
			for (int y = n - 2; y >= 0; y--) {
				for (int x = 0; x < n; x++) {
					int ny = y;
					while (ny < n - 1 && arr[ny + 1][x] == 0) {
						arr[ny + 1][x] = arr[ny][x];
						arr[ny][x] = 0;
						++ny;
					}

					if (ny < n - 1 && arr[ny + 1][x] == arr[ny][x] && !visit[ny + 1][x]) {
						visit[ny + 1][x] = true;
						arr[ny + 1][x] *= 2;
						arr[ny][x] = 0;
					}

				}
			}
			break;
		case 2:
			for (int x = 1; x < n; x++) {
				for (int y = 0; y < n; y++) {
					int nx = x;
					while (nx >= 1 && arr[y][nx - 1] == 0) {
						arr[y][nx - 1] = arr[y][nx];
						arr[y][nx] = 0;
						--nx;
					}
					if (nx >= 1 && arr[y][nx - 1] == arr[y][nx] && !visit[y][nx - 1]) {
						visit[y][nx - 1] = true;
						arr[y][nx - 1] *= 2;
						arr[y][nx] = 0;
					}
				}
			}
			break;
		case 3:
			for (int x = n - 2; x >= 0; x--) {
				for (int y = 0; y < n; y++) {
					int nx = x;
					while (nx < n - 1 && arr[y][nx + 1] == 0) {
						arr[y][nx + 1] = arr[y][nx];
						arr[y][nx] = 0;
						++nx;
					}
					if (nx < n - 1 && arr[y][nx + 1] == arr[y][nx] && !visit[y][nx + 1]) {
						visit[y][nx + 1] = true;
						arr[y][nx + 1] *= 2;
						arr[y][nx] = 0;
					}
				}
			}
			break;
		}
	}
}