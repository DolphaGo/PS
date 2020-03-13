import java.util.*;
import java.io.*;

public class boj2870 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<String> pq[] = new PriorityQueue[101];
		for (int i = 1; i <= 100; i++) {
			pq[i] = new PriorityQueue<>(new Comparator<String>() {
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				char c = s.charAt(j);
				// 숫자면
				if ('0' <= c && c <= '9') {
					int k = j + 1;
					while (k < s.length() && '0' <= s.charAt(k) && s.charAt(k) <= '9')
						++k;

					sb.append(s.substring(j, k));
					while (sb.length() > 1 && sb.charAt(0) == '0') {
						sb.deleteCharAt(0);
					}
					pq[sb.length()].add(sb.toString());
					sb.setLength(0);
					j = k;
				}
			}
		}
		for(int i=1;i<=100;i++) {
			while(!pq[i].isEmpty()) {
				sb.append(pq[i].poll()+"\n");
			}
		}
		System.out.println(sb);
	}
}
