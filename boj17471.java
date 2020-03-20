import java.util.*;
import java.io.*;
public class boj17471 {
	static ArrayList<Integer> list[];
	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		int people[]=new int[n+1];
		for(int i=1;i<=n;i++) people[i]=Integer.parseInt(st.nextToken());
		list=new ArrayList[n+1];
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			int num=Integer.parseInt(st.nextToken());
			list[i]=new ArrayList<Integer>();
			for(int j=0;j<num;j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		int answer=Integer.MAX_VALUE;
		ArrayList<Integer> A=new ArrayList<Integer>();
		ArrayList<Integer> B=new ArrayList<Integer>();
		for(int i=1;i<(1<<(n-1));i++) {
			int Asum=0;
			int Bsum=0;
			for(int j=0;j<n;j++) {
				if((i&(1<<j))>0) {
					A.add(j+1);
					Asum+=people[j+1];
				}
				else {
					B.add(j+1);
					Bsum+=people[j+1];
				}
			}
			if(A.size()>0&&B.size()>0) {
				if(Connected(A)&&Connected(B)) {
					int diff=Math.abs(Asum-Bsum);
					answer=answer>diff?diff:answer;
				}
			}
			A.clear();
			B.clear();
		}
		System.out.println(answer==Integer.MAX_VALUE?-1:answer);
	}
	static boolean Connected(ArrayList<Integer> l) {
		Queue<Integer> q=new LinkedList<Integer>();
		q.add(l.get(0));
		boolean visit[]=new boolean[n+1];
		visit[l.get(0)]=true;
		int dst=l.size();
		int cnt=1;
		while(!q.isEmpty()) {
			int p=q.poll();
			for(int val:list[p]) {
				if(!visit[val]&&l.contains(val)) {
					visit[val]=true;
					++cnt;
					q.add(val);
				}
			}
		}
		if(cnt==dst) return true;
		else return false;
	}
}
