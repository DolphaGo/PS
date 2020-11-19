import java.io.*;
import java.util.*;

public class boj16236_0601 {
	static int n,size,eat,sy,sx,answer;
	static int map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		
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
		answer=0;
		size=2;
		eat=0;
		while(true) {
			if(yes()) continue;
			else break;
		}
		System.out.println(answer);
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static boolean yes() {
		PriorityQueue<int[]> pq=new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				if(o1[2]==o2[2]&&o1[0]==o2[0]) return Integer.compare(o1[1],o2[1]);
				else if(o1[2]==o2[2]) return Integer.compare(o1[0],o2[0]);
				else return Integer.compare(o1[2],o2[2]);
			}
		});
		
		Queue<int[]> q=new LinkedList<int[]>();
		boolean v[][]=new boolean[n][n];
		v[sy][sx]=true;
		q.add(new int[] {sy,sx,0});
		while(!q.isEmpty()) {
			int[] p=q.poll();
			for(int i=0;i<4;i++) {
				int ny=p[0]+dy[i];
				int nx=p[1]+dx[i];
				if(ny>=0&&nx>=0&&ny<n&&nx<n&&map[ny][nx]<=size&&!v[ny][nx]) {
					v[ny][nx]=true;
					if(0<map[ny][nx]&&map[ny][nx]<size) pq.add(new int[] {ny,nx,p[2]+1});
					q.add(new int[] {ny,nx,p[2]+1});
				}
			}
		}
		
		if(pq.size()>0) {
			int[] pick=pq.poll();
			++eat;
			if(eat==size) {
				eat=0;
				++size;
			}
			sy=pick[0]; sx=pick[1];
			answer+=pick[2];
			map[sy][sx]=0;
			return true;
		}
		return false;
	}
}