import java.util.*;
import java.io.*;

public class boj15651 {
	static int n, m;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[m];
		go(0);
		System.out.print(sb.toString());
	}

	static void go(int v) {
		if (v == m) {
			for (int i = 0; i < m; i++)	sb.append(arr[i] + " ");
			sb.append("\n");
			return;
		}
		for (int i = 1; i <= n; i++) {
			arr[v] = i;
			go(v+1);
		}
	}
}