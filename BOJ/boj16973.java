import java.util.*;
import java.io.*;

public class boj16973 {
	static int h,w;
	static int map[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		map=new int[n][m];
		for(int y=0;y<n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<m;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		st=new StringTokenizer(br.readLine());
		h=Integer.parseInt(st.nextToken());
		w=Integer.parseInt(st.nextToken());
		int sr=Integer.parseInt(st.nextToken())-1;
		int sc=Integer.parseInt(st.nextToken())-1;
		int fr=Integer.parseInt(st.nextToken())-1;
		int fc=Integer.parseInt(st.nextToken())-1;
		
		boolean visit[][]=new boolean[n][m];
		visit[sr][sc]=true;
		Queue<int[]> q=new LinkedList<int[]>();
		q.add(new int[] {sr,sc,0});
		int dy[]= {-1,1,0,0};
		int dx[]= {0,0,-1,1};
		while(!q.isEmpty()) {
			int[] p=q.poll();
			if(p[0]==fr&&p[1]==fc) {
				System.out.println(p[2]);
				return;
			}
			for(int i=0;i<4;i++) {
				int ny=p[0]+dy[i];
				int	nx=p[1]+dx[i];
				if(ny>=0&&nx>=0&&ny+h-1<n&&nx+w-1<m&&!visit[ny][nx]) {
					if(check(i,ny,nx)) {
						visit[ny][nx]=true;
						q.add(new int[] {ny,nx,p[2]+1});
					}
				}
			}
		}
		System.out.println(-1);
	}
	static boolean check(int dir,int ny,int nx) {
		switch(dir) {
		case 0://위로 이동할 때
			for(int k=0;k<w;k++) {
				if(map[ny][nx+k]==1) return false;
			}
			break;
		case 1://아래로 이동할 때
			for(int k=0;k<w;k++) {
				if(map[ny+h-1][nx+k]==1) return false;
			}
			break;
		case 2://왼쪽으로 이동할 때
			for(int k=0;k<h;k++) {
				if(map[ny+k][nx]==1) return false;
			}
			break;
		case 3://오른쪽으로 이동할 때
			for(int k=0;k<h;k++) {
				if(map[ny+k][nx+w-1]==1) return false;
			}
			break;
		}
		return true;
	}
}