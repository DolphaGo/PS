import java.io.*;
import java.util.*;

public class boj17142_0601 {
	static int n,m,answer,zero;
	static int map[][];
	static ArrayList<int[]> virus=new ArrayList<int[]>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		map=new int[n][n];
		zero=0;
		for(int y=0;y<n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<n;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
				if(map[y][x]==2) virus.add(new int[] {y,x});
				if(map[y][x]==0) zero++;
			}
		}
		answer=Integer.MAX_VALUE;
		if(zero==0) answer=0;
		else go(0,0);
		System.out.println(answer==Integer.MAX_VALUE?-1:answer);
	}
	static ArrayList<int[]> choose=new ArrayList<int[]>();
	static void go(int v,int select) {
		if(select==m) {
			int res=Test(zero);
			answer=answer>res?res:answer;
			return;
		}
		if(v==virus.size()) return;
		choose.add(virus.get(v));
		go(v+1,select+1);
		choose.remove(select);
		go(v+1,select);
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static int Test(int z) {
		Queue<int[]> q=new LinkedList<int[]>();
		boolean visit[][]=new boolean[n][n];
		for(int i=0;i<m;i++) {
			int[] v=choose.get(i);
			q.add(new int[] {v[0],v[1],0});
			visit[v[0]][v[1]]=true;
		}
		while(!q.isEmpty()) {
			int[] p=q.poll();
			for(int i=0;i<4;i++) {
				int ny=p[0]+dy[i];
				int nx=p[1]+dx[i];
				if(ny>=0&&nx>=0&&ny<n&&nx<n&&map[ny][nx]!=1&&!visit[ny][nx]) {
					visit[ny][nx]=true;
					if(map[ny][nx]==0) --z;
					if(z==0) return p[2]+1;
					q.add(new int[] {ny,nx,p[2]+1});
				}
			}
		}
		return Integer.MAX_VALUE;
	}
}