import java.util.*;
import java.io.*;

public class boj17070 {
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
		// 가로 0, 세로, 1, 대각선 2
		answer = 0;
		go(0, 1, 0);
		System.out.println(answer);
	}

	static int dy[] = { 0, 1, 1 };
	static int dx[] = { 1, 1, 0 };

	static void go(int y, int x, int dir) {
		if (y == n - 1 && x == n - 1) {
			++answer;
			return;
		}
		for (int i = 0; i < 3; i++) {
			if((dir==0&&i==2)||(dir==2&&i==0)) continue; 
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny >= 0 && nx >= 0 && ny < n && nx < n && check(ny,nx,i)) {
				go(ny, nx, i);
			}
		}
	}

	static boolean check(int y, int x, int dir) {
		if(dir==1) {
			if(map[y-1][x]!=0||map[y][x]!=0||map[y][x-1]!=0) return false;
			else return true;
		}else return map[y][x]==0?true:false;
	}
}