import java.util.*;
import java.io.*;

public class boj17472_0603 {
	static int h,w;
	static int[][] map,box;
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static PriorityQueue<int[]> pq;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		h=Integer.parseInt(st.nextToken());
		w=Integer.parseInt(st.nextToken());
		map=new int[h][w];
		box=new int[h][w];
		for(int y=0;y<h;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<w;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		int num=Rendering();
		
		//MST => UnionFind로 구하자.
		int[] p=new int[num+1];
		for(int i=1;i<=num;i++) p[i]=i;
		
		//최소 비용 순으로 정렬하기.
		 pq=new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				return Integer.compare(o1[2],o2[2]);
			}
		});
		getBridge();
		int cnt=0;
		int answer=0;
		while(pq.size()>0&&cnt<num-1) {
			int[] poll=pq.poll();
			int a=poll[0];
			int b=poll[1];
			int c=poll[2];
			a=getParent(p,a);
			b=getParent(p,b);
			//서로 연결이 되어 있지 않다면 연결하기.
			if(a!=b) {
				if(a<b) p[b]=a;
				else p[a]=b;
				answer+=c;
				++cnt;
			}
		}
		System.out.println(cnt==num-1?answer:-1);
	}
	
	static int getParent(int[] p,int a) {
		if(p[a]==a) return a;
		return p[a]=getParent(p,p[a]);
	}
	
	static void getBridge() {
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				if(box[y][x]!=0) {
					for(int i=0;i<4;i++) {
						int ny=y+dy[i];
						int nx=x+dx[i];
						int len=0;
						while(isRange(ny,nx)&&box[ny][nx]==0) {
							++len;
							ny+=dy[i];
							nx+=dx[i];
						}
						if(isRange(ny,nx)&&len>=2&&box[ny][nx]!=box[y][x]) {
							pq.add(new int[] {box[y][x],box[ny][nx],len});
						}
					}
				}
			}
		}
		
		
	}
	static boolean isRange(int y,int x) {
		if(y>=0&&x>=0&&y<h&&x<w) return true;
		return false;
	}
	static int Rendering() {
		boolean visit[][]=new boolean[h][w];
		Queue<int[]> q=new LinkedList<int[]>();
		int idx=0;
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				if(map[y][x]==1&&!visit[y][x]) {
					visit[y][x]=true;
					box[y][x]=++idx;
					q.add(new int[] {y,x});
					while(!q.isEmpty()) {
						int[] p=q.poll();
						for(int i=0;i<4;i++) {
							int ny=p[0]+dy[i];
							int nx=p[1]+dx[i];
							if(ny>=0&&nx>=0&&ny<h&&nx<w&&map[ny][nx]==1&&!visit[ny][nx]) {
								visit[ny][nx]=true;
								box[ny][nx]=idx;
								q.add(new int[] {ny,nx});
							}
						}
					}
				}
			}
		}
		return idx;
	}
}