import java.util.*;
import java.io.*;

public class Main {
	static int h,w,answer;
	static int map[][];
	public static void main(String[] args) throws IOException{
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
		System.out.println(go(0));
	}
	
	static int go(int v) {
		//확인
		int res=Test();
		if(res>=2) {
			return v;
		}else if(res==0) return 0;
		else {
			melt();
			return go(v+1);
		}
		
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static void melt() {
		int len[][]=new int[h][w];
	
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				if(map[y][x]!=0) {
					int cnt=0;
					for(int i=0;i<4;i++) {
						int ny=y+dy[i];
						int nx=x+dx[i];
						if(ny>=0&&nx>=0&&ny<h&&nx<w&&map[ny][nx]==0) {
							++cnt;
						}
					}
					len[y][x]=cnt;
				}
			}
		}
		
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				if(map[y][x]!=0) {
					int val=map[y][x]-len[y][x];
					map[y][x]=val<=0?0:val;
				}
			}
		}
		
	}
	
	static int Test() {
		int cnt=0;
		Queue<int[]> q=new LinkedList<int[]>();
		boolean visit[][]=new boolean[h][w];
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				if(map[y][x]!=0&&!visit[y][x]) {
					visit[y][x]=true;
					++cnt;
					q.add(new int[] {y,x});
					
					while(!q.isEmpty()) {
						int[] p=q.poll();
						for(int i=0;i<4;i++) {
							int ny=p[0]+dy[i];
							int nx=p[1]+dx[i];
							if(ny>=0&&nx>=0&&ny<h&&nx<w&&map[ny][nx]!=0&&!visit[ny][nx]) {
								visit[ny][nx]=true;
								q.add(new int[] {ny,nx});
							}
						}
					}
				}
			}
		}
		return cnt;
	}
}