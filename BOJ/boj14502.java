import java.io.*;
import java.util.*;

public class boj14502 {
	static int h,w,answer;
	static int[][] map;
	static ArrayList<int[]> virus=new ArrayList<int[]>();
	static ArrayList<int[]> nom=new ArrayList<int[]>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		 h=Integer.parseInt(st.nextToken());
		 w=Integer.parseInt(st.nextToken());
		
		 map=new int[h][w];
		 for(int y=0;y<h;y++) {
			 st=new StringTokenizer(br.readLine());
			 for(int x=0;x<w;x++) {
				 map[y][x]=Integer.parseInt(st.nextToken());
				 if(map[y][x]==0) {
					 nom.add(new int[] {y,x});
				 }else if(map[y][x]==2) {
					 virus.add(new int[] {y,x});
				 }
			 }
		 }
		answer=Integer.MIN_VALUE;
		 go(0,0);
		 System.out.println(answer);
	}
	static void go(int v,int select) {
		if(select==3) {
			int val=getZero();
			answer=answer<val?val:answer;
			return;
		}
		if(v==nom.size()) return;
		
		int cur[]=nom.get(v);
		
		map[cur[0]][cur[1]]=1;
		go(v+1,select+1);
		map[cur[0]][cur[1]]=0;
		go(v+1,select);
	}
	static int getZero() {
		Queue<int[]> q=new LinkedList<int[]>();
		for(int[] p:virus) q.add(p);
		
		int[][] clonemap=new int[h][w];
		for(int y=0;y<h;y++) clonemap[y]=map[y].clone();
		
		
		int zero=0;
		int dy[]= {-1,1,0,0};
		int dx[]= {0,0,1,-1};
		while(!q.isEmpty()) {
			int[] p=q.poll();
			for(int i=0;i<4;i++) {
				int ny=p[0]+dy[i];
				int nx=p[1]+dx[i];
				if(ny>=0&&nx>=0&&ny<h&&nx<w&&clonemap[ny][nx]==0) {
					clonemap[ny][nx]=1;
					q.add(new int[] {ny,nx});
				}
			}
		}
		
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				if(clonemap[y][x]==0) {
					zero++;
				}
			}
		}
		return zero;
	}
}