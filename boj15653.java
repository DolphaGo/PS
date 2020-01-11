import java.util.*;
import java.io.*;

public class Main {
	static int h, w, answer;
	static char[][] map;
	static boolean[][][][] visit;

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
		Queue<int[]> q=new LinkedList<int[]>();
		Queue<Integer> c=new LinkedList<Integer>();
		q.add(R);q.add(B);
		c.add(0);
		while(!q.isEmpty()) {
			int[] red=q.poll();
			int[] blue=q.poll();
			int cnt=c.poll();
			
			if(map[red[0]][red[1]]=='O') {
				answer=cnt;
				break;
			}
			for(int i=0;i<4;i++) {
				int[] nR=move(red,i);
				int[] nB=move(blue,i);
				
				if(nR[0]==nB[0]&&nR[1]==nB[1]&&map[nR[0]][nR[1]]!='O') {
					int dR=diff(nR,red);
					int dB=diff(nB,blue);
					if(dR>dB) {
						nR[0]-=dy[i]; nR[1]-=dx[i];
					}else {
						nB[0]-=dy[i]; nB[1]-=dx[i];
					}
				}
				if(map[nB[0]][nB[1]]!='O'&&!visit[nR[0]][nR[1]][nB[0]][nB[1]]) {
					visit[nR[0]][nR[1]][nB[0]][nB[1]]=true;
					q.add(nR); q.add(nB); c.add(cnt+1);
				}
			}
		}
		System.out.println(answer==Integer.MAX_VALUE?-1:answer);
	}

	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
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