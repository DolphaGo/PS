import java.util.*;
import java.io.*;

public class boj15654 {
	static int n, m;
	static int[] arr,data;
	static boolean visit[];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		data=new int[n];
		visit=new boolean[n];
		arr = new int[m];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) data[i]=Integer.parseInt(st.nextToken());
		Arrays.sort(data);
		go(0);
		System.out.print(sb.toString());
	}

	static void go(int v) {
		if (v == m) {
			for (int i = 0; i < m; i++)	sb.append(arr[i] + " ");
			sb.append("\n");
			return;
		}
		for (int i = 0; i < n; i++) {
			if(!visit[i]) {
				visit[i]=true;
				arr[v]=data[i];
				go(v+1);
				visit[i]=false;
			}
		}
	}
}