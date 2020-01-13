import java.util.*;
import java.io.*;

public class Main {
	static int h, w, answer;
	static char[][] map;
	static boolean[][][][] visit;
	static ArrayList<Character> trace=new ArrayList<>();
	static ArrayList<Character> list=new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new char[h][w];
		visit = new boolean[h][w][h][w];
		int[] R = new int[2];
		int[] B = new int[2];
		for (int y = 0; y < h; y++) {
			map[y] = br.readLine().toCharArray();
			for (int x = 0; x < w; x++) {
				if (map[y][x] == 'R') {
					R[0] = y;
					R[1] = x;
					map[y][x] = '.';
				} else if (map[y][x] == 'B') {
					B[0] = y;
					B[1] = x;
					map[y][x] = '.';
				}
			}
		}
		answer = Integer.MAX_VALUE;
		visit[R[0]][R[1]][B[0]][B[1]] = true;
		go(0, R, B);
		if(answer==Integer.MAX_VALUE) System.out.println(-1);
		else {
			System.out.println(answer);
			for(int i=0;i<list.size();i++) {
				System.out.print(list.get(i));
			}
		}
	}

	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static char dir[]= {'U','D','L','R'};
	static void go(int v, int[] R, int[] B) {
		if (v > 10 || v>=answer)
			return;
		if (map[R[0]][R[1]] == 'O') {
			if(answer>v) {
				answer=v;
				list.clear();
				for(char val:trace) list.add(val);
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			int[] nR = move(R, i);
			int[] nB = move(B, i);

			// 구멍이 아닌 지점에서 두 위치가 같을 때,
			if (nR[0] == nB[0] && nR[1] == nB[1] && map[nR[0]][nR[1]] != 'O') {
				int dR = diff(R, nR);
				int dB = diff(B, nB);
				// 거리가 더 크다는 것은 더 뒤에 있었다는 것
				if (dR > dB) {
					nR[0] -= dy[i];
					nR[1] -= dx[i];
				} else {
					nB[0] -= dy[i];
					nB[1] -= dx[i];
				}
			}

			// 1. 실패하는 지점인 파란공이 빠졌을 때 제외
			// 2. 이미 방문했던 곳 제외
			if (map[nB[0]][nB[1]] != 'O' && !visit[nR[0]][nR[1]][nB[0]][nB[1]]) {
				visit[nR[0]][nR[1]][nB[0]][nB[1]] = true;
				trace.add(dir[i]);
				go(v + 1, nR, nB);
				trace.remove(trace.size()-1);
				visit[nR[0]][nR[1]][nB[0]][nB[1]] = false;
			}

		}
	}

	static int diff(int[] a, int[] b) {
		return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
	}

	static int[] move(int[] ball, int i) {
		int ny = ball[0];
		int nx = ball[1];
		while (true) {
			ny += dy[i];
			nx += dx[i];
			if (map[ny][nx] == '#')
				return new int[] { ny - dy[i], nx - dx[i] };
			else if (map[ny][nx] == 'O')
				return new int[] { ny, nx };
		}
	}
}