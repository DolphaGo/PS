import java.io.*;
import java.util.*;

public class boj10995 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < n; y++) {
			if (y % 2 == 0) {
				for (int x = 0; x < n; x++) {
					sb.append("* ");
				}
			} else {
				for (int x = 0; x < n; x++) {
					sb.append(" *");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}