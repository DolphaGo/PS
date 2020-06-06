import java.io.*;
import java.util.*;

class swea2117_0606 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int answer = 0;
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int map[][] = new int[n][n];
			ArrayList<int[]> list = new ArrayList<>();
			for (int y = 0; y < n; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < n; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
					if (map[y][x] == 1)
						list.add(new int[] { y, x });
				}
			}
			for (int y = 0; y < n; y++) {
				for (int x = 0; x < n; x++) {
					for (int k = 1; k <= n + 1; k++) {
						int cost=(k*k)+(k-1)*(k-1);
						int money=0;
						for (int i = 0; i < list.size(); i++) {
							int[] home=list.get(i);
							int len=Math.abs(home[0]-y)+Math.abs(home[1]-x);
							//len==0 -> 서비스 1에 해당 즉, len=n-> 서비스 k+1에 해당
							if(len<=k-1) money+=m; 
						}
						//손해를 보지 않는 선에서
						if(cost<=money) {
							int man=money/m;
							answer=answer<man?man:answer;
						}
					}
				}
			}
			sb.append("#" + tc + " " + answer + "\n");
		}
		System.out.print(sb);
	}
}	