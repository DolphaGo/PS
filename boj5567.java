import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n=Integer.parseInt(br.readLine());
		int m=Integer.parseInt(br.readLine());

		ArrayList<Integer> list[]=new ArrayList[n+1];
		for(int i=1;i<=n;i++) list[i]=new ArrayList<>();

		for(int i=0;i<m;i++){
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}

		int answer=0;
		Queue<int[]> q=new LinkedList<>();
		boolean visit[]=new boolean[n+1];
		visit[1]=true;
		q.add(new int[]{1,0});
		while(!q.isEmpty()){
			int[] p=q.poll();
			int now=p[0];
			if(p[1]==2) continue;
			for(int i=0;i<list[now].size();i++){
				int val=list[now].get(i);
				if(!visit[val]){
					++answer;
					visit[val]=true;
					q.add(new int[]{val,p[1]+1});
				}
			}
		}

		System.out.println(answer);
	}
}
