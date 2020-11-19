import java.util.*;
import java.io.*;

public class Main {
	static ArrayList<int[]> list[];
	static int answer;
	static boolean v[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n=Integer.parseInt(br.readLine());
		list=new ArrayList[n+1];
		v=new boolean[n+1];
		for(int i=1;i<=n;i++) list[i]=new ArrayList<>();
		for(int i=1;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			int p=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			list[p].add(new int[] {s,w});
			list[s].add(new int[] {p,w});
		}
		answer=0;
		for(int i=1;i<=n;i++) {
			Arrays.fill(v, false);
			v[i]=true;
			getR(i,0);
		}
		System.out.println(answer);
	}
	static void getR(int cur,int len) {
		for(int[] l:list[cur]) {
			int now=l[0];
			int cost=l[1];
			if(v[now]) continue;
			v[now]=true;
			getR(now,len+cost);
		}
		answer=answer<len?len:answer;
	}
}