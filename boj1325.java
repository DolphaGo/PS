import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());

		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());

		List<Integer> list[]=new ArrayList[n+1];
		for(int i=1;i<=n;i++) list[i]=new ArrayList();

		for(int i=0;i<m;i++){
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			list[b].add(a); //a가 b를 신뢰함
		}

		int[] res=new int[n+1];
		int max=0;
		Queue<Integer> q=new LinkedList<>();
		for(int i=1;i<=n;i++){
			boolean[] visit=new boolean[n+1];

			int cnt=1;
			q.add(i);
			visit[i]=true;
			while(!q.isEmpty()){
				int p=q.poll();
				for(int val:list[p]){
					if(visit[val]) continue;
					visit[val]=true;
					++cnt;
					q.add(val);
				}
			}
			res[i]=cnt;
			max=Math.max(max,cnt);
		}
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<=n;i++){
			if(res[i]==max) sb.append(i+" ");
		}
		System.out.print(sb.toString());
	}
}
