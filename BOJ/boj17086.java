import java.util.*;
import java.io.*;

public class boj17086 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int h=Integer.parseInt(st.nextToken());
		int w=Integer.parseInt(st.nextToken());
		int map[][]=new int[h][w];
		Queue<int[]> q=new LinkedList<>();
		for(int y=0;y<h;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<w;x++) {
				int val=Integer.parseInt(st.nextToken());
				if(val==1) {
					map[y][x]=0;
					q.add(new int[] {y,x});
				}
				else map[y][x]=Integer.MAX_VALUE/2;
			}
		}
		int dy[]= {-1,-1,-1,0,1,1,1,0};
		int dx[]= {-1,0,1,1,1,0,-1,-1};
		while(!q.isEmpty()) {
			int[] p =q.poll();
			int y=p[0];
			int x=p[1];
			for(int i=0;i<8;i++) {
				int ny=y+dy[i];
				int nx=x+dx[i];
				if(ny>=0&&nx>=0&&ny<h&&nx<w) {
					if(map[ny][nx]>map[y][x]+1) {
						map[ny][nx]=map[y][x]+1;
						q.add(new int[] {ny,nx});
					}
				}
			}
		}
		int ans=0;
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				ans=Math.max(map[y][x],ans);
			}
		}
		System.out.println(ans);
	}
}