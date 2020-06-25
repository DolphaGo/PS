import java.util.*;
import java.io.*;

public class boj19238 {
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static int map[][];
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int oil = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int ty = Integer.parseInt(st.nextToken()) - 1;
		int tx = Integer.parseInt(st.nextToken()) - 1;
		
		HashMap<Integer, int[]> hm=new HashMap<Integer, int[]>();
		for (int i = 2; i <= m + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int sy = Integer.parseInt(st.nextToken()) - 1;
			int sx = Integer.parseInt(st.nextToken()) - 1;
			int ey = Integer.parseInt(st.nextToken()) - 1;
			int ex = Integer.parseInt(st.nextToken()) - 1;
			map[sy][sx] = i;
			hm.put(i,new int[] {ey,ex});
		}
		Queue<int[]> q = new LinkedList<int[]>();
		PriorityQueue<int[]> list = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if (o1[2] == o2[2]) {
					if (o1[0] == o2[0])
						return Integer.compare(o1[1], o2[1]);
					return Integer.compare(o1[0], o2[0]);
				} else
					return Integer.compare(o1[2], o2[2]);
			}
		});
		int cnt = 0;
		while (cnt < m) {
			// 현재 위치에 손님이 있는지 검사
			if (map[ty][tx] >= 2) list.add(new int[] { ty, tx, 0 });
			else {
				//현재 위치에 손님이 없다면 가장 가까운 손님 탐색 시작
				q.add(new int[] { ty, tx, 0 });
				boolean visit[][] = new boolean[n][n];
				visit[ty][tx] = true;
				while (!q.isEmpty()) {
					int[] p = q.poll();
					for (int i = 0; i < 4; i++) {
						int ny = p[0] + dy[i];
						int nx = p[1] + dx[i];
						if (isOk(ny, nx) && !visit[ny][nx] && p[2] + 1 <= oil) {
							visit[ny][nx] = true;
							if (map[ny][nx] > 0) list.add(new int[] { ny, nx, p[2] + 1 });
							q.add(new int[] { ny, nx, p[2] + 1 });
						}
					}
				}
			}
			
			//연료 범위 내에 태울 손님이 있다면?
			if (list.size() > 0) {
				int[] p = list.poll();
				list.clear();
				// 손님을 태움
				int my = p[0];	int mx = p[1];
				int tar = map[my][mx];
				map[my][mx] = 0;
				oil -= p[2];
				boolean flag = false;
				//손님의 목적지 탐색 시작(목적지까지 모시겠습니다.)
				q.add(new int[] { my, mx, 0 });
				boolean[][] subvisit = new boolean[n][n];
				subvisit[my][mx] = true;
				while (!q.isEmpty()) {
					int[] subp = q.poll();
					for (int i = 0; i < 4; i++) {
						int ny = subp[0] + dy[i];
						int nx = subp[1] + dx[i];
						if (isOk(ny, nx) && !subvisit[ny][nx] && subp[2] + 1 <= oil) {
							subvisit[ny][nx] = true;
							// 손님 목적지 도착
							if (ny==hm.get(tar)[0]&&nx==hm.get(tar)[1]) {
								flag = true;
								// 한명 완료
								++cnt;
								// 택시 위치 업데이트
								ty = ny;
								tx = nx;
								// 오일 충전
								oil += subp[2] + 1;
							} else q.add(new int[] { ny, nx, subp[2] + 1 });
						}
					}
				}
				// 가다가 연료가 다 나갔으면 종료
				if (!flag)	break;
			} else break;
		}
		System.out.println(cnt < m ? -1 : oil);
	}

	// 범위 내에 있으면서 벽이 아닌지 검사하는 메서드
	static boolean isOk(int y, int x) {
		if (y >= 0 && x >= 0 && y < n && x < n && map[y][x] != 1) return true;
		return false;
	}
}