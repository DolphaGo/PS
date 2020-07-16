import java.util.*;
class Solution {
    public int solution(int[][] board) {
     	int n = board.length;
		int[][][] dist = new int[n][n][4];
		for (int y = 0; y < n; y++) {
			for(int x=0;x<n;x++) {
				Arrays.fill(dist[y][x], Integer.MAX_VALUE / 2);
			}
		}
		PriorityQueue<int[]> q = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[3], o2[3]);
			}
		});
		// 상,하,좌,우
		int dy[] = { -1, 1, 0, 0 };
		int dx[] = { 0, 0, -1, 1 };
		
		if (board[0][1] == 0) {
			dist[0][1][3] = 100;
			q.add(new int[] { 0, 1, 3, 100 });
		}
		if (board[1][0] == 0) {
			dist[1][0][1] = 100;
			q.add(new int[] { 1, 0, 1, 100 });
		}

		boolean visit[][][]=new boolean[n][n][4];
		while (!q.isEmpty()) {
			int[] p = q.poll();
			int y=p[0];
			int x=p[1];
			int dir=p[2];
			if(visit[y][x][dir]) continue;
			visit[y][x][dir]=true;
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				// 범위 안에 있고, 갈 수 있을 때
				if (isRange(ny, nx, n) && board[ny][nx] == 0) {
					int cost = dir == i ? 100 : 600;
					if (dist[ny][nx][i] > p[3] + cost) {
						dist[ny][nx][i] = p[3] + cost;
						q.add(new int[] { ny, nx, i, dist[ny][nx][i] });
					}
				}
			}
		}
		int min=Integer.MAX_VALUE;
		for(int i=0;i<4;i++) {
			min=Math.min(min,dist[n-1][n-1][i]);
		}
		return min;
	}

	static boolean isRange(int y, int x, int n) {
		return (y >= 0 && x >= 0 && y < n && x < n);
	}
}