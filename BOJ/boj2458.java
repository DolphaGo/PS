import java.util.*;
import java.io.*;

public class boj2458 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> up[]=new ArrayList[n+1];
		ArrayList<Integer> dn[]=new ArrayList[n+1];
		for(int i=1;i<=n;i++) {
			up[i]=new ArrayList<Integer>();
			dn[i]=new ArrayList<Integer>();
		}
		
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			// a<b 라는 의미
			up[a].add(b);
			dn[b].add(a);
		}
		int ans=0;
		Queue<Integer> q=new LinkedList<Integer>();
		for(int i=1;i<=n;i++) {
			boolean v[]=new boolean[n+1];
			v[i]=true;
			q.add(i);
			int ucount=0;
			while(!q.isEmpty()) {
				int p=q.poll();
				for(int val:up[p]) {
					if(v[val]) continue;
					v[val]=true;
					q.add(val);
					++ucount;
				}
			}
			
			Arrays.fill(v, false);
			v[i]=true;
			q.add(i);
			int dcount=0;
			while(!q.isEmpty()) {
				int p=q.poll();
				for(int val:dn[p]) {
					if(v[val]) continue;
					v[val]=true;
					q.add(val);
					++dcount;
				}
			}
			if(ucount+dcount==n-1) ++ans;
		}
		System.out.println(ans);
	}
}