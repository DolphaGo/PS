import java.util.*;
import java.io.*;

public class boj14222 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		boolean v[]=new boolean[n+1];
		st=new StringTokenizer(br.readLine());
		Queue<Integer> q=new LinkedList<Integer>();
		int cnt=0;
		for(int i=0;i<n;i++) {
			int val=Integer.parseInt(st.nextToken());
			if(val<=n&&!v[val]) {
				v[val]=true;
				++cnt;
			}
			else q.add(val);
		}
		while(!q.isEmpty()) {
			int p=q.poll()+k;
			if(p<=n&&!v[p]) {
				v[p]=true;
				++cnt;
			}else if(p<=n) q.add(p);
		}
		if(cnt==n) System.out.println(1);
		else System.out.println(0);
	}
}
