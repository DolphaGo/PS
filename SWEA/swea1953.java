import java.util.*;
import java.io.*;
public class Solution {
	
	//위, 아래, 왼쪽, 오른쪽
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		
		ArrayList<Integer> type[]=new ArrayList[8];
		for(int i=1;i<=7;i++) {
			type[i]=new ArrayList<Integer>();
			switch(i) {
			case 1:
				for(int dir=0;dir<4;dir++) type[i].add(dir);
				break;
			case 2:
				type[i].add(0); type[i].add(1);
				break;
			case 3:
				type[i].add(2); type[i].add(3);
				break;
			case 4:
				type[i].add(0); type[i].add(3);
				break;
			case 5:
				type[i].add(1); type[i].add(3);
				break;
			case 6:
				type[i].add(1); type[i].add(2);
				break;
			case 7:
				type[i].add(0); type[i].add(2);
			}
		}
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			int h=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			int my=Integer.parseInt(st.nextToken());
			int mx=Integer.parseInt(st.nextToken());
			int l=Integer.parseInt(st.nextToken());
			
			int map[][]=new int[h][w];
			for(int y=0;y<h;y++) {
				st=new StringTokenizer(br.readLine());
				for(int x=0;x<w;x++) {
					map[y][x]=Integer.parseInt(st.nextToken());
				}
			}
			
			Queue<int[]> q=new LinkedList<int[]>();
			boolean visit[][]=new boolean[h][w];
			visit[my][mx]=true;
			q.add(new int[] {my,mx,1});
			int answer=1;
			while(!q.isEmpty()) {
				int[] p=q.poll();
				int y=p[0];
				int x=p[1];
				int time=p[2];
				int pipe=map[y][x];
				for(int d:type[pipe]) {
					int ny=y+dy[d];
					int nx=x+dx[d];
					if(ny>=0&&nx>=0&&ny<h&&nx<w&&map[ny][nx]>0&&!visit[ny][nx]&&type[map[ny][nx]].contains(hasDIR(d))) {
						visit[ny][nx]=true;
						if(time+1<=l) {
							++answer;
							q.add(new int[] {ny,nx,time+1});
						}
					}
				}
			}
			System.out.println("#"+tc+" "+answer);
		}
	}
	static int hasDIR(int cur) {
		if(cur==0||cur==1) return 1-cur;
		else return 5-cur;
	}
}
