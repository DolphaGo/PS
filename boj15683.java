import java.io.*;
import java.util.*;

public class boj15683 {
	static int h,w,answer;
	static int map[][];
	static ArrayList<int[]> cctv=new ArrayList<int[]>();
	static Stack<int[]> trace=new Stack<>();
	static int dy[]= {-1,0,1,0};
	static int dx[]= {0,1,0,-1};
	static int[][][] range= {
			{ {0} , {1}, {2}, {3} },
			{ {0,2}, {1,3} },
			{ {0,1}, {1,2}, {2,3}, {3,0} },
			{ {0,1,2}, {1,2,3}, {2,3,0}, {3,0,1} },
			{ {0,1,2,3} }
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		h=Integer.parseInt(st.nextToken());
		w=Integer.parseInt(st.nextToken());
		map=new int[h][w];
		int zero=0;
		for(int y=0;y<h;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<w;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
				if(1<=map[y][x]&&map[y][x]<=5) {
					cctv.add(new int[] {y,x});
				}
				if(map[y][x]==0) ++zero;
			}
		}
		answer=Integer.MAX_VALUE;
		go(0,zero);
		System.out.println(answer);
	}
	static void go(int v,int res) {
		if(v==cctv.size()) {
			answer=answer>res?res:answer;
			return;
		}
		
		int[] cur=cctv.get(v);
		int y=cur[0];
		int x=cur[1];
		int type=map[y][x]-1;
		for(int i=0;i<range[type].length;i++) {
			int erz=0;
			for(int j=0;j<range[type][i].length;j++) {
				int ddy=dy[range[type][i][j]];
				int ddx=dx[range[type][i][j]];
				int ny=y+ddy;
				int nx=x+ddx;
				while(ny>=0&&nx>=0&&ny<h&&nx<w&&map[ny][nx]!=6) {
					if(map[ny][nx]==0) {
						++erz;
						map[ny][nx]=7;
						trace.add(new int[] {ny,nx});
					}
					ny+=ddy;
					nx+=ddx;
				}
			}
			go(v+1,res-erz);
			for(int iter=0;iter<erz;iter++) {
				int[] p=trace.pop();
				map[p[0]][p[1]]=0;
			}
		}
	}
}