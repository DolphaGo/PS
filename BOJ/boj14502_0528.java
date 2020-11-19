import java.io.*;
import java.util.*;

public class boj14502_0528 {
	static int h,w,answer;
	static int map[][];
	static ArrayList<int[]> list=new ArrayList<int[]>();
	static ArrayList<int[]> virus=new ArrayList<>();
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
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
				if(map[y][x]==0) {
					list.add(new int[] {y,x});
				}else if(map[y][x]==2) {
					virus.add(new int[] {y,x});
				}
			}
		}
		answer=0;
		go(0,0);
		System.out.println(answer);
	}
	static void go(int v,int select) {
		if(select==3) {
			int[][] test=new int[h][w];
			for(int y=0;y<h;y++) test[y]=map[y].clone();
			go(test);
			return;
		}
		if(v==list.size()) return;
		
		int cur[]=list.get(v);
		map[cur[0]][cur[1]]=1;
		go(v+1,select+1);
		map[cur[0]][cur[1]]=0;
		go(v+1,select);
	}
	
	static void go(int[][] test) {
		Queue<int[]> q=new LinkedList<int[]>();
		int zero=list.size()-3;
		for(int i=0;i<virus.size();i++) q.add(virus.get(i));
		
		while(!q.isEmpty()) {
			int[] p=q.poll();
			for(int i=0;i<4;i++) {
				int ny=p[0]+dy[i];
				int nx=p[1]+dx[i];
				if(ny>=0&&nx>=0&&ny<h&&nx<w&&test[ny][nx]==0) {
					--zero;
					test[ny][nx]=2;
					q.add(new int[] {ny,nx});
				}
			}
		}
		answer=answer<zero?zero:answer;
	}
}