import java.util.*;
import java.io.*;

public class boj11003 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb=new StringBuilder();
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		int[] data = new int[n];
		for (int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(st.nextToken());
			while (!dq.isEmpty() && data[dq.getLast()] > data[i]) dq.removeLast();
			dq.addLast(i);
			if (!dq.isEmpty() && dq.getFirst() <= i - l) dq.removeFirst();
			sb.append(data[dq.getFirst()]).append(" ");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}