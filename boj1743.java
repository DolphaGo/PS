import java.io.*;
import java.util.*;

public class boj1743 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int h=Integer.parseInt(st.nextToken());
		int w=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		
		boolean[][] map=new boolean[h][w];
		for(int i=0;i<k;i++) {
			st=new StringTokenizer(br.readLine());
			int y=Integer.parseInt(st.nextToken())-1;
			int x=Integer.parseInt(st.nextToken())-1;
			map[y][x]=true;
		}
		
		int dy[]= {-1,1,0,0};
		int dx[]= {0,0,-1,1};
		
		boolean[][] visit=new boolean[h][w];
		Queue<int[]> q=new LinkedList<int[]>();
		int answer=0;
		
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				if(map[y][x]&&!visit[y][x]) {
					visit[y][x]=true;
					int size=1;
					q.add(new int[] {y,x});
					while(!q.isEmpty()) {
						int[] p=q.poll();
						for(int i=0;i<4;i++) {
							int ny=p[0]+dy[i];
							int nx=p[1]+dx[i];
							if(ny>=0&&nx>=0&&ny<h&&nx<w&&map[ny][nx]&&!visit[ny][nx]) {
								visit[ny][nx]=true;
								q.add(new int[] {ny,nx});
								++size;
							}
						}
					}
					answer=answer<size?size:answer;
				}
			}
		}
		System.out.println(answer);
		
	}
}
