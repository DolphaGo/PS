import java.util.*;
import java.io.*;

public class boj6679 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();
		int val[] = new int[3];
		for (int i = 1000; i <= 9999; i++) {
			val[0] = go(10, i);
			val[1] = go(12, i);
			val[2] = go(16, i);
			if (val[0] == val[1] && val[1] == val[2]) {
				sb.append(i + "\n");
			}
		}
		System.out.println(sb);
	}

	static int go(int reg, int val) {
		int res = 0;
		while (val > 0) {
			res += val % reg;
			val /= reg;
		}
		return res;
	}
}
