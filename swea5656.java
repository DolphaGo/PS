import java.util.*;
import java.io.*;

class swea5656 {
	static int n,w,h,answer;
	static int map[][];
	static int turn[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer =0;
			st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			w=Integer.parseInt(st.nextToken());
			h=Integer.parseInt(st.nextToken());
			map=new int[h][w];
			for(int y=0;y<h;y++) {
				st=new StringTokenizer(br.readLine());
				for(int x=0;x<w;x++) {
					map[y][x]=Integer.parseInt(st.nextToken());
				}
			}
			//중복 순열
			turn=new int[n];
			answer=Integer.MAX_VALUE;
			go(0);
			sb.append("#" + tc + " " + answer + "\n");
		}
		System.out.print(sb);
	}
	static void go(int select) {
		if(select==n) {
			int ret=Test();
			answer=answer>ret?ret:answer;
			return;
		}
		for(int i=0;i<w;i++) {
			turn[select]=i;
			go(select+1);
		}
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static int Test() {
		int[][] tmp=new int[h][w];
		for(int y=0;y<h;y++) tmp[y]=map[y].clone();
		
		Queue<int[]> q=new LinkedList<int[]>();
		for(int i=0;i<n;i++) {
			boolean visit[][]=new boolean[h][w];
			int wx=turn[i];
			int wy=0;
			while(wy<h&&tmp[wy][wx]==0) ++wy;
			if(wy==h) continue;
			
			q.add(new int[] {wy,wx,tmp[wy][wx]});
			tmp[wy][wx]=0;
			while(!q.isEmpty()) {
				int[] p=q.poll();
				for(int dir=0;dir<4;dir++) {
					for(int len=0;len<p[2];len++) {
						int ny=p[0]+len*dy[dir];
						int nx=p[1]+len*dx[dir];
						if(ny>=0&&nx>=0&&ny<h&&nx<w&&!visit[ny][nx]) {
							visit[ny][nx]=true;
							if(tmp[ny][nx]>0) {
								q.add(new int[] {ny,nx,tmp[ny][nx]});
								tmp[ny][nx]=0;
							}
						}
					}
				}
			}
			for(int y=h-2;y>=0;y--) {
				for(int x=0;x<w;x++) {
					int ny=y;
					while(ny<h-1&&tmp[ny][x]!=0&&tmp[ny+1][x]==0) {
						tmp[ny+1][x]=tmp[ny][x];
						tmp[ny++][x]=0;
					}
				}
			}
		}
		int ret=0;
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				if(tmp[y][x]>0) ++ret;
			}
		}
		return ret;
	}
}