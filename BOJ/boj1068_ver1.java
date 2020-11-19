import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n=Integer.parseInt(br.readLine());
		int[] p=new int[n];
		int[] ch=new int[n];
		boolean[] visit=new boolean[n];
		st=new StringTokenizer(br.readLine());

		/**
		 * 부모 번호와 자식 수(0,1,2)
		 */
		for(int i=0;i<n;i++){
			p[i]=Integer.parseInt(st.nextToken());
			if(p[i]!=-1) ch[p[i]]++;
		}

		Queue<Integer> q=new LinkedList<>();
		int del=Integer.parseInt(br.readLine());
		visit[del]=true;
		q.add(del);
		if(p[del]!=-1) ch[p[del]]--;

		while(!q.isEmpty()){
			int poll=q.poll();
			for(int i=0;i<n;i++){
				if(p[i]==poll&&!visit[i]){
					visit[i]=true;
					q.add(i);
					ch[poll]--;
				}
			}
		}
		int answer=0;
		for(int i=0;i<n;i++){
			if(!visit[i]&&ch[i]==0) ++answer;
		}
		System.out.println(answer);
	}
}
