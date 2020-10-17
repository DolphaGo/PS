import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int[][] map;
	static int[] dy= {-1,1,0,0};
	static int[] dx= {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		int sy=0,sx=0,size=2,eat=0;
		for(int y=0;y<n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<n;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
				if(map[y][x]==9) {
					map[y][x]=0;
					sy=y;
					sx=x;
				}
			}
		}
		
		int answer=0;
		while(true) {
			int[] res=bfs(sy,sx,size);
			if(res==null) break;
			sy=res[0];
			sx=res[1];
			answer+=res[2];
			map[sy][sx]=0; //상어가 먹음.
			++eat;
			if(eat==size) {
				eat=0;
				++size;
			}
		}
		System.out.println(answer);
	}
	static int[] bfs(int sy,int sx,int size) {
		Queue<int[]> q=new LinkedList<int[]>();
		boolean[][] visit=new boolean[n][n];
		visit[sy][sx]=true;
		q.add(new int[] {sy,sx,0});

		PriorityQueue<int[]> res=new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				if(o1[2]==o2[2]) {
					if(o1[0]==o2[0]) return Integer.compare(o1[1], o2[1]);
					else return Integer.compare(o1[0], o2[0]);
				}else return Integer.compare(o1[2],o2[2]);
			}
		});
		while(!q.isEmpty()) {
			int[] p=q.poll();
			
			for(int i=0;i<4;i++) {
				int ny=p[0]+dy[i];
				int nx=p[1]+dx[i];
				if(isRange(ny,nx)&&map[ny][nx]<=size&&!visit[ny][nx]) {
					visit[ny][nx]=true;
					q.add(new int[] {ny,nx,p[2]+1});
					if(0<map[ny][nx]&&map[ny][nx]<size) res.add(new int[] {ny,nx,p[2]+1}); //잡아 먹을 수 있는 목록
				}
			}
		}
		if(res.size()==0) return null;
		else return res.poll();
	}
	static boolean isRange(int y,int x) {
		return 0<=y&&y<n&&0<=x&&x<n;
	}
}