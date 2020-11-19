import java.util.*;
import java.io.*;

public class Main {
	static int h,w;
	static int[][] map,renderMap;
	static int[] dy= {-1,1,0,0};
	static int[] dx= {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
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
		renderMap=new int[h][w];
		int cnt=render();
		int[] p=new int[cnt+1];
		for(int i=1;i<=cnt;i++) p[i]=i;
		
		PriorityQueue<int[]> pq=connect();
		int conn=0;
		int answer=0;
		while(conn<cnt-1&&!pq.isEmpty()) {
			int[] poll=pq.poll();
			int a=poll[0];
			int b=poll[1];
			int dis=poll[2];
			a=find(p,a);
			b=find(p,b);
			if(a!=b) {
				if(a>b) p[b]=a;
				else p[a]=b;
				answer+=dis;
				++conn;
			}
		}
		System.out.println(conn==cnt-1?answer:-1);
	}
	
	static int find(int[] p,int a) {
		if(p[a]==a) return a;
		else return p[a]=find(p,p[a]);
	}
	
	static int render() {
		Queue<int[]> q=new LinkedList<int[]>();
		boolean[][] visit=new boolean[h][w];
		int cnt=0;
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				if(map[y][x]==1&&!visit[y][x]) {
					visit[y][x]=true;
					q.add(new int[] {y,x});
					renderMap[y][x]= ++cnt;
					while(!q.isEmpty()) {
						int[] p=q.poll();
						
						for(int i=0;i<4;i++) {
							int ny=p[0]+dy[i];
							int nx=p[1]+dx[i];
							if(isRange(ny,nx)&&map[ny][nx]==1&&!visit[ny][nx]) {
								visit[ny][nx]=true;
								renderMap[ny][nx]=cnt;
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
		return 0<=y&&y<h&&0<=x&&x<w;
	}
	
	static PriorityQueue<int[]> connect(){
		PriorityQueue<int[]> pq=new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				if(renderMap[y][x]>0) {
					for(int i=0;i<4;i++) {
						int ny=y+dy[i];
						int nx=x+dx[i];
						int len=0;
						while(isRange(ny,nx)&&renderMap[ny][nx]==0) {
							++len;
							ny+=dy[i];
							nx+=dx[i];
						}
						
						if(isRange(ny,nx)&&renderMap[ny][nx]!=renderMap[y][x]&&len>=2) {
							pq.add(new int[] {renderMap[y][x],renderMap[ny][nx],len});
						}
					}
				}
			}
		}
		return pq;
	}
}