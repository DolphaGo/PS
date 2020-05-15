import java.util.*;
import java.io.*;

public class boj14889 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int p[][] = new int[n][n];
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				p[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		ArrayList<Integer> A = new ArrayList<Integer>();
		ArrayList<Integer> B = new ArrayList<Integer>();
		int answer = Integer.MAX_VALUE;
		for (int i = (1 << n / 2) - 1; i < (1 << n - 1); i++) {
			A.clear();
			B.clear();
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) > 0) A.add(j);
				else B.add(j);
			}
			if (A.size() != B.size()) continue;
			int Aval = 0;
			int Bval = 0;
			for(int a=0;a<A.size();a++) {
				int val=A.get(a);
				for(int j=0;j<A.size();j++) Aval+=p[val][A.get(j)];
			}
			for(int b=0;b<B.size();b++) {
				int val=B.get(b);
				for(int j=0;j<B.size();j++) Bval+=p[val][B.get(j)];
			}
			
			int res = Math.abs(Aval - Bval);
			answer = answer > res ? res : answer;
		}
		System.out.println(answer);

	}
}