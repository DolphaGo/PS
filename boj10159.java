import java.util.*;
import java.io.*;

public class boj10159 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N=Integer.parseInt(br.readLine());
		int M=Integer.parseInt(br.readLine());
		
		ArrayList<Integer> up[]=new ArrayList[N+1];
		ArrayList<Integer> dn[]=new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			up[i]=new ArrayList<>();
			dn[i]=new ArrayList<>();
		}
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			//a>b라는 뜻.
			up[b].add(a);
			dn[a].add(b);
		}
		StringBuilder sb=new StringBuilder();
		Queue<Integer> q=new LinkedList<>();
		boolean visit[]=new boolean[N+1];
		for(int i=1;i<=N;i++) {
			int cnt=1;
			Arrays.fill(visit, false);
			q.add(i);
			visit[i]=true;
			while(!q.isEmpty()) {
				int p=q.poll();
				for(int val:up[p]) {
					if(!visit[val]) {
						visit[val]=true;
						q.add(val);
						++cnt;
					}
				}
			}
			Arrays.fill(visit, false);
			q.add(i);
			visit[i]=true;
			while(!q.isEmpty()) {
				int p=q.poll();
				for(int val:dn[p]) {
					if(!visit[val]) {
						visit[val]=true;
						q.add(val);
						++cnt;
					}
				}
			}
			sb.append(N-cnt);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}