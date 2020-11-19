import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int map[][]=new int[5][5];
		Queue<int[]> q=new LinkedList<>();
		for(int y=0;y<5;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<5;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
				q.add(new int[] {y,x,map[y][x],1});
			}
		}
		boolean visit[]=new boolean[1000000];
		int dy[]= {-1,1,0,0};
		int dx[]= {0,0,-1,1};
		int answer=0;
		while(!q.isEmpty()) {
			int[] p=q.poll();
			int y=p[0];	int x=p[1]; int val=p[2]; int len=p[3];
			
			if(len==6) {
				if(!visit[val]) {
					visit[val]=true;
					++answer;
				}
				continue;
			}
			for(int i=0;i<4;i++) {
				int ny=y+dy[i];
				int nx=x+dx[i];
				if(ny>=0&&nx>=0&&ny<5&&nx<5) {
					int next=val*10+map[ny][nx];
					q.add(new int[] {ny,nx,next,len+1});
				}
			}
		}
		System.out.println(answer);
	}
}
