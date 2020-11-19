import java.io.*;
import java.util.*;

public class boj13908 {
	static int n,m,answer;
	static boolean digit[]=new boolean[10];
	static int push[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) {
			digit[Integer.parseInt(st.nextToken())]=true;
		}
		push=new int[n];
		answer=0;
		dfs(0);
		System.out.println(answer);
	}
	static boolean chk[]=new boolean[10];
	static void dfs(int v) {
		if(v==n) {
			int cnt=0;
			for(int i=0;i<n;i++) {
				if(digit[push[i]]&&!chk[push[i]]) {
					chk[push[i]]=true;
					++cnt;
				}
			}
			if(cnt==m) answer++;
			Arrays.fill(chk, false);
			return;
		}
		for(int i=0;i<=9;i++) {
			push[v]=i;
			dfs(v+1);
		}
	}
}
