import java.util.*;
import java.io.*;

public class boj15649 {
	static int n,m;
	static boolean[] visit;
	static int[] arr;
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		visit=new boolean[n+1];
		arr=new int[m];
		go(0);
		System.out.print(sb.toString());
	}
	static void go(int v) {
		if(v==m) {
			for(int i=0;i<m;i++) sb.append(arr[i]+" ");
			sb.append("\n");
			return;
		}
		for(int i=1;i<=n;i++) {
			if(!visit[i]) {
				visit[i]=true;
				arr[v]=i;
				go(v+1);
				visit[i]=false;
			}
		}
	}
}