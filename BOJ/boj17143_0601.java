import java.io.*;
import java.util.*;

public class boj17143_0601 {
	static class Info {
		int s, d, z;
		Info(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Info map[][] = new Info[h][w];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			if (d <= 1) //0,1(위,아래)
				s %= (2 * h - 2);
			else //2,3 (오른,왼)
				s %= (2 * w - 2);
			map[r][c] = new Info(s, d, z);
		}

		int answer = 0;

		int dy[]= {-1,1,0,0};
		int dx[]= {0,0,1,-1};
		
		PriorityQueue<Info> pq[][] = new PriorityQueue[h][w];
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				pq[y][x] = new PriorityQueue<>(new Comparator<Info>() {
					public int compare(Info o1, Info o2) {
						return Integer.compare(o2.z, o1.z);
					}
				});
			}
		}

		for (int i = 0; i < w; i++) {
			// 낚시
			for (int y = 0; y < h; y++) {
				if (map[y][i] != null) {
					answer += map[y][i].z;
					map[y][i] = null;
					break;
				}
			}
			// 이동
			for(int y=0;y<h;y++) {
				for(int x=0;x<w;x++) {
					if(map[y][x]!=null) {
						int ny=y;
						int nx=x;
						for(int ss=0;ss<map[y][x].s;ss++) {
							ny+=dy[map[y][x].d];
							nx+=dx[map[y][x].d];
							if(ny==-1||nx==-1||ny==h||nx==w) {
								if(map[y][x].d<=1) map[y][x].d=1-map[y][x].d;
								else map[y][x].d=5-map[y][x].d;
								//다시 뒷방향으로 돌아갈 것
								ny+=2*dy[map[y][x].d];
								nx+=2*dx[map[y][x].d];
							}
						}
						pq[ny][nx].add(map[y][x]);
						map[y][x]=null;//현재 물고기는 없애주자.
					}
				}
			}
			
			//가장 큰 상어 하나만 맵에 업데이트
			for(int y=0;y<h;y++) {
				for(int x=0;x<w;x++) {
					if(pq[y][x].size()>0) {
						map[y][x]=pq[y][x].poll();
						pq[y][x].clear();
					}
				}
			}
		}
		System.out.println(answer);
	}
}