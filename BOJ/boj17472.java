import java.util.*;
import java.io.*;

public class boj17472 {
	static int h,w;
	static int[][] map,cmap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		h=Integer.parseInt(st.nextToken());
		w=Integer.parseInt(st.nextToken());
		map=new int[h][w];
		for(int y=0;y<h;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<w;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		cmap=new int[h][w];
		int cnt=color();
		int[] p=new int[cnt+1];
		for(int i=1;i<=cnt;i++) p[i]=i;
		
		PriorityQueue<int[]> pq=new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				if(cmap[y][x]!=0) {
					int start=cmap[y][x];
					for(int i=0;i<4;i++) {
						int ny=y+dy[i];
						int nx=x+dx[i];
						int len=0;
						while(isRange(ny,nx)&&cmap[ny][nx]==0) {
							++len;
							ny+=dy[i];
							nx+=dx[i];
						}
						if(isRange(ny,nx)&&cmap[ny][nx]!=start&&len>=2) {
							pq.add(new int[] {start,cmap[ny][nx],len});
						}
					}
				}
			}
		}
		
		int n=0;
		int answer=0;
		while(!pq.isEmpty()&&n<cnt-1) {
			int[] poll=pq.poll();
			int a=poll[0];
			int b=poll[1];
			int c=poll[2];
			a=getParent(p,a);
			b=getParent(p,b);
			if(a!=b) {
				if(a>b) p[a]=b;
				else p[a]=b;
				answer+=c;
				++n;
			}
		}
		System.out.println(n==cnt-1?answer:-1);
	}
	static int getParent(int[] p,int a) {
		if(p[a]==a) return a;
		return p[a]=getParent(p,p[a]);
	}

	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static int color() {
		Queue<int[]> q=new LinkedList<int[]>();
		boolean visit[][]=new boolean[h][w];
		int cnt=0;
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				if(map[y][x]==1&&!visit[y][x]) {
					visit[y][x]=true;
					cmap[y][x]=++cnt;
					q.add(new int[] {y,x});
					while(!q.isEmpty()) {
						int[] p=q.poll();
						for(int i=0;i<4;i++) {
							int ny=p[0]+dy[i];
							int nx=p[1]+dx[i];
							if(isRange(ny,nx)&&map[ny][nx]==1&&!visit[ny][nx]) {
								visit[ny][nx]=true;
								cmap[ny][nx]=cnt;
								q.add(new int[] {ny,nx});
							}
						}
					}
				}
			}
		}
		return cnt;
	}
	static boolean isRange(int y,int x) {
		if(y>=0&&x>=0&&y<h&&x<w) return true;
		return false;
	}
}