import java.io.*;
import java.util.*;
class boj15686_0601 {
	static int n,m,answer;
	static int map[][];
	static ArrayList<int[]> list=new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		map=new int[n][n];
		for(int y=0;y<n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<n;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
				if(map[y][x]==2) {
					list.add(new int[] {y,x});
				}
			}
		}
		answer=Integer.MAX_VALUE;
		go(0,0);
		System.out.println(answer);
	}
	static ArrayList<int[]> SelectedList=new ArrayList<int[]>();
	static void go(int v,int select) {
		if(select==m) {
			int ret=bfs();
			answer=answer>ret?ret:answer;
			return;
		}
		if(v==list.size()) return;
		SelectedList.add(list.get(v));
		go(v+1,select+1);
		SelectedList.remove(select);
		go(v+1,select);
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static int bfs() {
		Queue<int[]> q=new LinkedList<>();
		boolean[][] visit=new boolean[n][n];
		for(int i=0;i<SelectedList.size();i++) {
			int[] p=SelectedList.get(i);
			visit[p[0]][p[1]]=true;
			q.add(new int[] {p[0],p[1],0});
		}
		int ret=0;
		while(!q.isEmpty()) {
			int[] p=q.poll();
			int y=p[0];
			int x=p[1];
			int d=p[2];
			for(int i=0;i<4;i++) {
				int ny=y+dy[i];
				int nx=x+dx[i];
				if(ny>=0&&nx>=0&&ny<n&&nx<n&&!visit[ny][nx]) {
					visit[ny][nx]=true;
					q.add(new int[] {ny,nx,d+1});
					if(map[ny][nx]==1) ret+=d+1;
				}
			}
		}
		return ret;
	}
}