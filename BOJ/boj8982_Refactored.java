import java.util.*;
import java.io.*;

public class boj8982_Refactored {
	static int h,w,answer;
	static int[][] map;
	static ArrayList<int[]> list=new ArrayList<int[]>();
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
				if(map[y][x]==0) list.add(new int[] {y,x});
			}
		}
		answer=0;
		go(0,0);
		System.out.println(answer);
	}
	static void go(int v,int s) {
		if(s==2) {
			answer=Math.max(bfs(),answer);
			return;
		}
		if(v==list.size()) return;
		
		int cur[]=list.get(v);
		map[cur[0]][cur[1]]=1;
		go(v+1,s+1);
		map[cur[0]][cur[1]]=0;
		go(v+1,s);
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static int bfs() {
		int res=0;
		boolean visit[][]=new boolean[h][w];
		//검은색 군집기준을 바라보자
		//검은색 군집기준에서 주변에 빈공간이 하나라도 있으면
		//잡아먹히는게 아니다.
		Queue<int[]> q=new LinkedList<>();
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				if(map[y][x]==2&&!visit[y][x]) {
					boolean flag=true;
					visit[y][x]=true;
					q.add(new int[] {y,x});
					int cnt=1;
					while(!q.isEmpty()) {
						int[] p=q.poll();
						for(int i=0;i<4;i++) {
							int ny=p[0]+dy[i];
							int nx=p[1]+dx[i];
							//범위를 벗어나거나 흰돌인 경우엔 처리하지 않는다.
							if(ny>=0&&nx>=0&&ny<h&&nx<w) {
								if(map[ny][nx]==2&&!visit[ny][nx]){
									visit[ny][nx]=true;
									q.add(new int[] {ny,nx});
									++cnt;
								}else if(map[ny][nx]==0) flag=false;
							}
						}
					}
					if(flag) res+=cnt;
				}
			}
		}
		return res;
	}
}