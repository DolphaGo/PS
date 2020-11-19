import java.util.*;
import java.io.*;

public class boj17471_0516_2_map버전 {
	static int n;
	static ArrayList<Integer> list[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		int[] p=new int[n+1];
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
		TreeMap<Integer,Boolean> Ateam,Bteam;
		int answer=Integer.MAX_VALUE;
		for(int i=1;i<(1<<n-1);i++) { //1개 이상을 보장함
			Ateam=new TreeMap<>();
			Bteam=new TreeMap<>();
			int Ares=0;
			int Bres=0;
			for(int j=0;j<n;j++) {
				if((i&(1<<j))>0) {
					Ateam.put(j+1,true);
					Ares+=p[j+1];
				}
				else {
					Bteam.put(j+1,true);
					Bres+=p[j+1];
				}
			}

		    if(isConnected(Ateam)&&isConnected(Bteam)) {
		    	answer=Math.min(answer, Math.abs(Ares-Bres));
		    }
		}
		System.out.println(answer==Integer.MAX_VALUE?-1:answer);
	}
	static boolean isConnected(TreeMap<Integer,Boolean> team) {
		boolean visit[]=new boolean[n+1];
		Queue<Integer> q=new LinkedList<Integer>();
		q.add(team.firstKey());
		visit[team.firstKey()]=true;
		int size=team.size()-1;
		while(!q.isEmpty()) {
			int poll = q.poll();
			for(int val:list[poll]) {
				if(!visit[val]&&team.containsKey(val)) {
					visit[val]=true;
					q.add(val);
					--size;
				}
			}
		}
		return size==0?true:false;
	}
	
}