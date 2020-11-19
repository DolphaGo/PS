import java.io.*;
import java.util.*;

public class boj15684_0529 {
	static int n, h, answer;
	static int arr[][];
	static ArrayList<int[]> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		arr = new int[h+1][n+1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			arr[a][b] = 1;
		}
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < n; x++) {
				if (arr[y][x] == 0)
					list.add(new int[] { y, x });
			}
		}
		answer = Integer.MAX_VALUE;
		go(0,0);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	static void go(int select,int next) {
		if (select > 3 || select >= answer)
			return;
		if (Ok()) {
			answer = answer > select ? select : answer;
			return;
		}
		
		for(int i=next;i<list.size();i++) {
			int cur[]=list.get(i);
			int y=cur[0];
			int x=cur[1];
			boolean flag=true;
			if (x - 1 >= 0 && arr[y][x - 1] == 1)
				flag = false;
			if (x + 1 < n && arr[y][x + 1] == 1)
				flag = false;
			if (flag) {
				arr[y][x] = 1;
				go(select + 1, i+1);
				arr[y][x] = 0;
			}
		}
	}

	static boolean Ok() {
		for (int x = 0; x < n; x++) {
			int i = x;
			int y = 0;
			while (y < h) {
				if (i - 1 >= 0 && arr[y][i - 1] == 1) {
					i -= 1;
				} else if (arr[y][i] == 1) {
					i += 1;
				}
				++y;
			}
			if (i != x)
				return false;
		}
		return true;
	}
}