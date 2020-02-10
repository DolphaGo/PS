import java.io.*;
import java.util.*;

public class boj15686 {
	static int answer,n,m;
	static int[][] map;
	static boolean[][] check;
	static ArrayList<int[]> chicken=new ArrayList<int[]>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		map=new int[n][n];
		check=new boolean[n][n];
		for(int y=0;y<n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<n;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
				if(map[y][x]==2) {
					chicken.add(new int[] {y,x});
				}
			}
		}
		answer=Integer.MAX_VALUE;
		go(0,0);
		System.out.println(answer);
	}
	
	static void go(int v,int select) {
		if(select==m) {
			int val=getdistance();
			answer=answer>val?val:answer;
			return;
		}
		if(v==chicken.size()) return;

		int[] cur=chicken.get(v);
		int y=cur[0];
		int x=cur[1];
		
		check[y][x]=true;
		go(v+1,select+1);
		check[y][x]=false;
		go(v+1,select);
	}
	static int getdistance() {
		int dis=0;
		boolean[][] temp=new boolean[n][n];
		int[] dy= {-1,1,0,0};
		int[] dx ={0,0,-1,1};
		
		Queue<int[]> q=new LinkedList<int[]>();
		for(int y=0;y<n;y++) {
			for(int x=0;x<n;x++) {
				if(map[y][x]==1) {
					for(int j=0;j<n;j++) Arrays.fill(temp[j], false);
					temp[y][x]=true;
					q.add(new int[] {y,x,0}); //처음 거리
					while(!q.isEmpty()) {
						int[] p=q.poll();
						for(int i=0;i<4;i++) {
							int ny=p[0]+dy[i];
							int nx=p[1]+dx[i];
							if(ny>=0&&nx>=0&&ny<n&&nx<n&&!temp[ny][nx]) {
								if(check[ny][nx]) {
									dis+=p[2]+1;
									q.clear();
									break;
								}else {
									temp[ny][nx]=true;
									q.add(new int[] {ny,nx,p[2]+1});
								}
							}
						}
					}
				}
			}
		}
		return dis;
		
	}
}