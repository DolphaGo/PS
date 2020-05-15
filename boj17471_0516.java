import java.util.*;
import java.io.*;

public class boj17471_0516 {
	static int n,p[];
	static ArrayList<Integer> list[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		p=new int[n+1];
		st=new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) p[i]=Integer.parseInt(st.nextToken());
		list=new ArrayList[n+1];
		for(int i=1;i<=n;i++) {
			list[i]=new ArrayList<Integer>();
			st=new StringTokenizer(br.readLine());
			int num=Integer.parseInt(st.nextToken());
			for(int j=0;j<num;j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		//00001~01111까지만
		ArrayList<Integer> Ateam,Bteam;
		int answer=Integer.MAX_VALUE;
		for(int i=1;i<(1<<n-1);i++) { //1개 이상을 보장함
			Ateam=new ArrayList<Integer>();
			Bteam=new ArrayList<Integer>();
			for(int j=0;j<n;j++) {
				if((i&(1<<j))>0) Ateam.add(j+1);
				else Bteam.add(j+1);
			}
			int Ares=isConnected(Ateam);
		    int Bres=isConnected(Bteam);

		    if(Ares>0&&Bres>0) {
		    	answer=Math.min(answer, Math.abs(Ares-Bres));
		    	if(answer==6) {
		    		System.out.println(Ateam.toString()+","+Ares);
		    		System.out.println(Bteam.toString()+","+Bres);
		    	}
		    }
		}
		System.out.println(answer==Integer.MAX_VALUE?-1:answer);
	}
	static int isConnected(ArrayList<Integer> team) {
		boolean visit[]=new boolean[n+1];
		Queue<Integer> q=new LinkedList<Integer>();
		q.add(team.get(0));
		visit[team.get(0)]=true;
		int res=p[team.get(0)];
		int size=team.size()-1;
		while(!q.isEmpty()) {
			int poll = q.poll();
			for(int val:list[poll]) {
				if(!visit[val]&&team.contains(val)) {
					visit[val]=true;
					q.add(val);
					res+=p[val];
					--size;
				}
			}
		}
		return size==0?res:0;
	}
	
}