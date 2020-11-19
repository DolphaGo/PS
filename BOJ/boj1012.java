import java.io.*;
import java.util.*;

public class Main {
	static int dy[]={-1,1,0,0};
	static int dx[]={0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++){
			st=new StringTokenizer(br.readLine());

			int w=Integer.parseInt(st.nextToken());
			int h=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());

			boolean[][] map=new boolean[h][w];
			for(int i=0;i<k;i++){
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				map[y][x]=true;
			}

			Queue<int[]> q=new LinkedList<>();
			boolean[][] visit=new boolean[h][w];
			int cnt=0;
			for(int y=0;y<h;y++){
				for(int x=0;x<w;x++){
					if(map[y][x]&&!visit[y][x]){
						++cnt;
						q.add(new int[]{y,x});
						while(!q.isEmpty()){
							int[] p=q.poll();
							for(int i=0;i<4;i++){
								int ny=p[0]+dy[i];
								int nx=p[1]+dx[i];
								if(ny>=0&&nx>=0&&ny<h&&nx<w&&!visit[ny][nx]&&map[ny][nx]){
									visit[ny][nx]=true;
									q.add(new int[]{ny,nx});
								}
							}
						}
					}
				}
			}
			sb.append(cnt+"\n");
		}
		System.out.println(sb);
	}
}
