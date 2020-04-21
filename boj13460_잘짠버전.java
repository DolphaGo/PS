
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj13460_잘짠버전 {
	static final int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int N, M;
	static char[][] map;
	static boolean[][][][] visit;
	static Queue<Info> que;

	static class Ball {
		int r, c;

		public Ball(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	static class Info {
		Ball blue;
		Ball red;
		int cnt;

		public Info(Ball blue, Ball red, int cnt) {
			super();
			this.blue = blue;
			this.red = red;
			this.cnt = cnt;
		}
	}

	static Ball move(Ball now, int d) {
		Ball next = null;
		int r = now.r;
		int c = now.c;

		while (true) {
			r += dir[d][0];
			c += dir[d][1];
			if (map[r][c] == '#') {
				next = new Ball(r - dir[d][0], c - dir[d][1]);
				break;
			} else if (map[r][c] == 'O') {
				next = new Ball(r, c);
				break;
			}
		}
		return next;
	}

	static int beforeAfterLen(Ball now, Ball next) {
		return Math.abs(now.r - next.r) + Math.abs(now.c - next.c);
	}

	static int bfs() {
		visit = new boolean[N][M][N][M];

		while (!que.isEmpty()) {
			Info now = que.poll();
			Ball nowR = now.red;
			Ball nowB = now.blue;
			if (now.cnt > 10) {
				return -1;
			}

			if (map[nowR.r][nowR.c] == 'O') {
				if (map[nowB.r][nowB.c] == 'O') {
					continue;
				}
				return now.cnt;
			}

			for (int d = 0; d < 4; d++) {
				Ball nextR = move(nowR, d);
				Ball nextB = move(nowB, d);

				if (map[nextR.r][nextR.c] != 'O') {
					if (nextR.r == nextB.r && nextR.c == nextB.c) {
						if (beforeAfterLen(nowR, nextR) > beforeAfterLen(nowB, nextB)) {
							nextR.r-=dir[d][0];
							nextR.c-=dir[d][1];
						} else {
							nextB.r-=dir[d][0];
							nextB.c-=dir[d][1];
						}
					}
				}

				if (!visit[nextR.r][nextR.c][nextB.r][nextB.c] && map[nextB.r][nextB.c] != 'O') {
					visit[nextR.r][nextR.c][nextB.r][nextB.c] = true;
					que.add(new Info(nextB, nextR, now.cnt + 1));
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N][M];
		que = new LinkedList<>();

		Ball red = null;
		Ball blue = null;
		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'R') {
					red = new Ball(i, j);
				} else if (map[i][j] == 'B') {
					blue = new Ball(i, j);
				}
			}
		}
		que.add(new Info(blue, red, 0));
		System.out.println(bfs());
	}
}