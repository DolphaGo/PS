import java.io.*;
import java.util.*;

public class boj1726 {
	static int dy[] = { 0, 0, 1, -1 };
	static int dx[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());

		int map[][] = new int[h][w];
		for (int y = 0; y < h; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < w; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		int Info[][] = new int[2][3];

		for (int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				int val = Integer.parseInt(st.nextToken()) - 1;
				Info[i][j] = val;
			}
		}

		boolean v[][][] = new boolean[4][h][w];

		PriorityQueue<int[]> q = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				return Integer.compare(o1[3], o2[3]);
			}
		});
		v[Info[0][2]][Info[0][0]][Info[0][1]]=true;
		q.add(new int[] { Info[0][0], Info[0][1], Info[0][2], 0 });
		int answer = 0;
		while (!q.isEmpty()) {
			int[] p = q.poll();//이게 최단이다.
			int cy = p[0];
			int cx = p[1];
			int cd = p[2];
			
			if (cy == Info[1][0] && cx == Info[1][1] && cd == Info[1][2]) {
				answer = p[3];
				break;
			}
			
			// Command 1 : 우선 현재 방향에서 직진을 해본다. (3가지)
			int ny = cy ;
			int nx = cx ;
			for(int i=0;i<3;i++) { //1 2 3
				ny+=dy[cd];
				nx+=dx[cd];
				if(ny>=0&&nx>=0&&ny<h&&nx<w&&map[ny][nx]==0) {
					if(!v[cd][ny][nx]) {
						v[cd][ny][nx]=true;
						q.add(new int[] {ny,nx,cd,p[3]+1});
					}
				}else break; //낮은수부터 못가면 그만.(직진개념)
			}
			
			
			// Command 2: 방향 왼쪽, 오른쪽이 있음.
			// 0 -> 3,2
			// 1 -> 2,3
			// 2 -> 0,1
			// 3 -> 1,0
			int left=0;
			int right=0;
			if(cd==0) {
				left=3;
				right=2;
			}else if(cd==1) {
				left=2;
				right=3;
			}else if(cd==2) {
				left=0;
				right=1;
			}else {
				left=1;
				right=0;
			}
			
			if(!v[left][cy][cx]) {
				v[left][cy][cx]=true;
				q.add(new int[] {cy,cx,left,p[3]+1});
			}
			
			if(!v[right][cy][cx]) {
				v[right][cy][cx]=true;
				q.add(new int[] {cy,cx,right,p[3]+1});
			}
			
		}
		System.out.println(answer);
	}
}