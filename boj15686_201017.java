import java.util.*;
import java.io.*;

public class Main {
	static int n,m,answer;
	static int[][] map;
	static List<int[]> list,choose;
	static int[] dy= {-1,1,0,0};
	static int[] dx= {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		map=new int[n][n];
		list=new ArrayList<int[]>();
		for(int y=0;y<n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<n;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
				if(map[y][x]==2) list.add(new int[] {y,x});
			}
		}
		
		answer=Integer.MAX_VALUE;
		choose=new ArrayList<>();
		go(0,0);
		System.out.println(answer);
	}
	static void go(int v,int select) {
		if(select==m) {
			int res=simulation();
			answer=Math.min(answer, res);
			return;
		}
		
		if(v==list.size()) return;
		choose.add(list.get(v));
		go(v+1,select+1);
		choose.remove(select);
		go(v+1,select);
	}
	static int simulation() {
		int res=0;
		Queue<int[]> q=new LinkedList<int[]>();
		boolean[][] visit=new boolean[n][n];
		for(int i=0;i<m;i++) {
			int[] p=choose.get(i);
			visit[p[0]][p[1]]=true;
			q.add(new int[] {p[0],p[1],0});
		}
		while(!q.isEmpty()) {
			int[] p=q.poll();
			for(int i=0;i<4;i++) {
				int ny=p[0]+dy[i];
				int nx=p[1]+dx[i];
				if(isRange(ny,nx)&&!visit[ny][nx]) {
					visit[ny][nx]=true;
					if(map[ny][nx]==1) res+=p[2]+1;
					q.add(new int[] {ny,nx,p[2]+1});
				}
			}
		}
		return res;
	}
	static boolean isRange(int y,int x) {
		return 0<=y&&y<n&&0<=x&&x<n;
	}
}
