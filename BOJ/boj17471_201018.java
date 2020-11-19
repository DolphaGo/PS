import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int[] man;
	static List<Integer>[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		man=new int[n+1];
		st=new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) {
			man[i]=Integer.parseInt(st.nextToken());
		}
		
		list=new ArrayList[n+1];
		for(int i=1;i<=n;i++) {
			list[i]=new ArrayList<>();
			st=new StringTokenizer(br.readLine());
			int cnt=Integer.parseInt(st.nextToken());
			for(int j=0;j<cnt;j++) {
				int next=Integer.parseInt(st.nextToken());
				list[i].add(next);
			}
		}
		
		int answer=Integer.MAX_VALUE;
		List<Integer> a=new ArrayList<Integer>();
		List<Integer> b=new ArrayList<Integer>();
		for(int i=1;i<1<<(n-1);i++) {
			int acnt=0,bcnt=0;
			for(int j=0;j<n;j++) {
				if((i&(1<<j))>0){
					acnt+=man[j+1];
					a.add(j+1);//인덱스를 넣는 것
				}else {
					bcnt+=man[j+1];
					b.add(j+1);//인덱스를 넣는 것이므로
				}
			}
			
			if(ok(a)&&ok(b)) {
				int diff=Math.abs(acnt-bcnt);
				answer=Math.min(answer, diff);
			}
			a.clear(); b.clear();
		}
		System.out.println(answer==Integer.MAX_VALUE?-1:answer);
	}
	static boolean ok(List<Integer> team) {
		Queue<Integer> q=new LinkedList<Integer>();
		boolean[] visit=new boolean[n+1];
		int head=team.get(0);
		q.add(head);
		visit[head]=true;
		int cnt=1;
		while(!q.isEmpty()) {
			int p=q.poll();
			for(int next:list[p]) {
				if(!visit[next]&&team.contains(next)) {
					visit[next]=true;
					q.add(next);
					++cnt;
				}
			}
		}
		if(cnt==team.size()) return true;
		else return false;
	}
}