import java.util.*;
import java.io.*;

public class boj17471_0603 {
	static int n,answer;
	static int p[];
	static ArrayList<Integer> list[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		p=new int[n+1];
		list=new ArrayList[n+1];
		st=new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) {
			p[i]=Integer.parseInt(st.nextToken());
			list[i]=new ArrayList<Integer>();
		}
		
		//연결리스트 생성
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			int len=Integer.parseInt(st.nextToken());
			for(int j=0;j<len;j++) {
				int l=Integer.parseInt(st.nextToken());
				list[i].add(l);
			}
		}
		answer=Integer.MAX_VALUE;
		ArrayList<Integer> A=new ArrayList<Integer>();
		ArrayList<Integer> B=new ArrayList<Integer>();
		int pa=0,pb=0;
		for(int i=1;i<(1<<n-1);i++) {
			//0000001~0111111 까지(최소 한 구역&중복 방지)
			for(int j=0;j<n;j++) {
				if((i&(1<<j))>0) {
					pa+=p[j+1];
					A.add(j+1);
				}
				else {
					pb+=p[j+1];
					B.add(j+1);
				}
			}
			
			if(Ok(A)&&Ok(B)) {
				int diff=Math.abs(pa-pb);
				answer=answer>diff?diff:answer;
			}
			A.clear(); B.clear();
			pa=0; pb=0;
		}
		System.out.println(answer==Integer.MAX_VALUE?-1:answer);
	}
	static boolean Ok(ArrayList<Integer> region) {
		int len=region.size();
		Queue<Integer> q=new LinkedList<Integer>();
		boolean visit[]=new boolean[n+1];
		for(int i=0;i<region.size();i++) {
			visit[region.get(0)]=true;
			q.add(region.get(0));
		}
		int cnt=1;
		while(!q.isEmpty()) {
			int p=q.poll();
			for(int i=0;i<list[p].size();i++) {
				int val=list[p].get(i);
				if(region.contains(val)&&!visit[val]) {
					visit[val]=true;
					++cnt;
					q.add(val);
				}
			}
		}
		if(len==cnt) return true;
		else return false;
	}
}