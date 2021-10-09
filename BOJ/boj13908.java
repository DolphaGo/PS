import java.io.*;
import java.util.*;

public class Main {
	static int n, m, known, answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// 0~9까지 즉, 숫자를 비트로 표현하면 (1<<10 -1)로 표현할 수 있다.
		known = 0;
		if (m > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				final int value = Integer.parseInt(st.nextToken());
				known |= 1 << value;
			}
		}

		answer = 0;
		dfs(0, 0);
		System.out.println(answer);
	}

	static void dfs(int v, int cnt) {
		if (v == n) {
			if (cnt == m) {
				++answer;
			}
			return;
		}
		for (int i = 0; i <= 9; i++) {
			if ((known & (1 << i)) > 0) {
				known ^= (1 << i);
				dfs(v + 1, cnt + 1);
				known |= (1 << i);
			} else {
				dfs(v + 1, cnt);
			}
		}
	}
}
