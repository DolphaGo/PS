import java.io.*;
import java.util.*;

public class boj3190_0527 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n=Integer.parseInt(br.readLine());
		int map[][]=new int[n][n];
		int k=Integer.parseInt(br.readLine());
		for(int i=0;i<k;i++) {
			st=new StringTokenizer(br.readLine());
			int y=Integer.parseInt(st.nextToken())-1;
			int x=Integer.parseInt(st.nextToken())-1;
			map[y][x]=1;
		}
		Deque<int[]> q=new LinkedList<int[]>();
		q.add(new int[] {0,0});
		map[0][0]=2;
		int l=Integer.parseInt(br.readLine());
		int time=0;
		int dy[]= {-1,0,1,0};
		int dx[]= {0,1,0,-1};
		int d=1;
		boolean flag=true;
		loop:for(int i=0;i<l;i++) {
			st=new StringTokenizer(br.readLine());
			int sec=Integer.parseInt(st.nextToken());
			char dir=st.nextToken().charAt(0);
			while(time<sec) {
				++time;
				int[] head=q.getFirst();
				int ny=head[0]+dy[d];
				int nx=head[1]+dx[d];
				if(ny>=0&&nx>=0&&ny<n&&nx<n) {
					if(map[ny][nx]==0) {
						int[] tail=q.pollLast();
						map[tail[0]][tail[1]]=0;
						map[ny][nx]=2;
						q.addFirst(new int[] {ny,nx});
					}else if(map[ny][nx]==1) {
						map[ny][nx]=2;
						q.addFirst(new int[] {ny,nx});
					}else {
						flag=false;
						break loop;
					}
				}else {
					flag=false;
					break loop;
				}
			}
			if(dir=='L') d=(d+3)%4;
			else d=(d+1)%4;
		}
		while(flag) {
			++time;
			int[] head=q.getFirst();
			int ny=head[0]+dy[d];
			int nx=head[1]+dx[d];
			if(ny>=0&&nx>=0&&ny<n&&nx<n) {
				if(map[ny][nx]==0) {
					int[] tail=q.pollLast();
					map[tail[0]][tail[1]]=0;
					map[ny][nx]=2;
					q.addFirst(new int[] {ny,nx});
				}else if(map[ny][nx]==1) {
					map[ny][nx]=2;
					q.addFirst(new int[] {ny,nx});
				}else {
					break;
				}
			}else break;
		}
		System.out.println(time);
	}
}