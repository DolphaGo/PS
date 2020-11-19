import java.util.*;
import java.io.*;

public class boj17141 {
	static int N,M,answer,zero;
	static int map[][];
	static int virus[];
	static ArrayList<int[]> list=new ArrayList<int[]>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][N];
		virus=new int[M];
		zero=0;
		for(int y=0;y<N;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<N;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
				if(map[y][x]==2) {
					list.add(new int[] {y,x});
					map[y][x]=0;
				}
				if(map[y][x]!=1) ++zero;
			}
		}
		answer=Integer.MAX_VALUE;
		go(0,0);
		System.out.println(answer==Integer.MAX_VALUE?-1:answer);
	}
	static void go(int v,int select) {
		if(select==M) {
			answer=Math.min(spread(), answer);
			return;
		}
		if(v==list.size()) return;
		
		virus[select]=v;
		go(v+1,select+1);
		go(v+1,select);
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static int spread() {
		Queue<int[]> q=new LinkedList<int[]>();
		boolean visit[][]=new boolean[N][N];
		for(int i=0;i<M;i++) {
			int[] get=list.get(virus[i]);
			visit[get[0]][get[1]]=true;
			q.add(new int[] {get[0],get[1],0});
		}
		
		int cnt=M;
		int time=0;
		while(!q.isEmpty()) {
			int[] p=q.poll();
			for(int i=0;i<4;i++) {
				int ny=p[0]+dy[i];
				int nx=p[1]+dx[i];
				if(ny>=0&&nx>=0&&ny<N&&nx<N&&map[ny][nx]==0&&!visit[ny][nx]) {
					visit[ny][nx]=true;
					q.add(new int[] {ny,nx,p[2]+1});
					time=Math.max(p[2]+1, time);
					++cnt;
				}
			}
		}
		return cnt<zero?Integer.MAX_VALUE:time;
	}
}