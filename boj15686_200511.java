import java.util.*;
import java.io.*;

public class boj15686_200511 {
	static int n,m,answer;
	static int map[][],choose[];
	static ArrayList<int[]> chicken=new ArrayList<int[]>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		map=new int[n][n];
		choose=new int[m];
		for(int y=0;y<n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<n;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
				if(map[y][x]==2) {
					chicken.add(new int[] {y,x});
					map[y][x]=0;
				}
			}
		}
		answer=Integer.MAX_VALUE;
		go(0,0);
		System.out.println(answer);
	}
	static void go(int v,int select) {
		if(select==m) {
			int res=calc();
			answer=Math.min(answer,res);
			return;
		}
		if(v==chicken.size()) return;
		choose[select]=v;
		go(v+1,select+1);
		go(v+1,select);
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static int calc() {
		boolean v[][]=new boolean[n][n];
		Queue<int[]> q=new LinkedList<int[]>();
		for(int i=0;i<m;i++) {
			int idx=choose[i];
			int[] chic=chicken.get(idx);
			v[chic[0]][chic[1]]=true;
			q.add(new int[] {chic[0],chic[1],0});
		}
		int res=0;
		while(!q.isEmpty()) {
			int[] p=q.poll();
			for(int i=0;i<4;i++) {
				int ny=p[0]+dy[i];
				int nx=p[1]+dx[i];
				if(ny>=0&&nx>=0&&ny<n&&nx<n&&!v[ny][nx]) {
					if(map[ny][nx]==1) res+=p[2]+1;
					v[ny][nx]=true;
					q.add(new int[] {ny,nx,p[2]+1});
				}
			}
		}
		return res;
	}
}