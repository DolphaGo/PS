import java.util.*;
import java.io.*;

public class boj17136_0603 {
	static int answer, one;
	static int map[][] = new int[10][10];
	static int paper[] = { 0, 5, 5, 5, 5, 5 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		one = 0;
		for (int y = 0; y < 10; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < 10; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				if (map[y][x] == 1)
					++one;
			}
		}
		answer = Integer.MAX_VALUE;
		go(0, 0, 0);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	static void go(int sy, int erz, int use) {
		if (use >= answer)	return;
		if (erz == one) {
			answer = use;
			return;
		}
		for (int y = sy; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				if (map[y][x] == 1) {
					for (int size = 1; size <= 5; size++) {
						if (paper[size] == 0) continue;
						
						boolean flag = true;
						loop: for (int yy = y; yy < y + size; yy++) {
							for (int xx = x; xx < x + size; xx++) {
								if (yy >= 10 || xx >= 10 || map[yy][xx] == 0) {
									flag=false;
									break loop;
								}
							}
						}
						if (flag) {
							--paper[size];
							for (int yy = y; yy < y + size; yy++) {
								for (int xx = x; xx < x + size; xx++) {
									map[yy][xx] = 0;
								}
							}
							go(y, erz + size * size, use + 1);
							for (int yy = y; yy < y + size; yy++) {
								for (int xx = x; xx < x + size; xx++) {
									map[yy][xx] = 1;
								}
							}
							++paper[size];
						}
					}
					return;
				}
			}
		}
	}
}