import java.util.*;
import java.io.*;

public class boj11660_쿼드트리풀이_시간초과 {
	static int n;
	static int arr[][], tree[];

	static int init(int node, int ay, int by, int ax, int bx) {
		if (ax > bx || ay > by)
			return 0;
		// leaf-node이면
		if (ax == bx && ay == by) {
			return tree[node] = arr[by][bx];
		} else {
			int my = (ay + by) / 2;
			int mx = (ax + bx) / 2;
			return tree[node] = init(node * 4 - 2, ay, my, ax, mx) + init(node * 4 - 1, my + 1, by, ax, mx)
					+ init(node * 4, ay, my, mx + 1, bx) + init(node * 4 + 1, my + 1, by, mx + 1, bx);
		}
	}

	static int sum(int node, int ay, int by, int ax, int bx, int ly, int lx, int ry, int rx) {
		if (ly > by|| ry < ay || lx > bx || rx < ax)
			return 0;
		
		if (ly <= ay && by <= ry && lx <= ax && bx <= rx) {
			return tree[node];
		} else {
			int my = (ay + by) / 2;
			int mx = (ax + bx) / 2;
			return sum(node * 4 - 2, ay, my, ax, mx, ly, lx, ry, rx)
					+ sum(node * 4 - 1, my + 1, by, ax, mx, ly, lx, ry, rx)
					+ sum(node * 4, ay, my, mx + 1, bx, ly, lx, ry, rx)
					+ sum(node * 4 + 1, my + 1, by, mx + 1, bx, ly, lx, ry, rx);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new int[n + 1][n + 1];
		
		int h = (int) Math.ceil(Math.log(n * n) / Math.log(4));
		//최대 리프 노드 수 : 4^h 
		tree = new int[summation(4,h)+1];

		for (int y = 1; y <= n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 1; x <= n; x++) {
				arr[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		init(1, 1, n, 1, n);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int res = sum(1, 1, n, 1, n, y1, x1, y2, x2);
			sb.append(res + "\n");
		}
		System.out.println(sb);
	}
	static int summation(int a,int b) {
		return b==0?1:pow(a,b)+summation(a,b-1);
	}
	static int pow(int a,int b) {
		return b==0?1:a*pow(a,b-1);
	}
}