import java.io.*;
import java.util.*;

class swea1949 {
	static int answer,n,k;
	static int map[][];
	static boolean visit[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			answer=0;
			
			st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			k=Integer.parseInt(st.nextToken());
			
			map=new int[n][n];
			visit=new boolean[n][n];
			ArrayList<int[]> list=new ArrayList<int[]>();
			int max=Integer.MIN_VALUE;
			for(int y=0;y<n;y++) {
				st=new StringTokenizer(br.readLine());
				for(int x=0;x<n;x++) {
					map[y][x]=Integer.parseInt(st.nextToken());
					if(max<map[y][x]) {
						max=map[y][x];
						list.clear();
						list.add(new int[] {y,x});
					}else if(max==map[y][x]) {
						list.add(new int[] {y,x});
					}
				}
			}
			
			for(int i=0;i<list.size();i++) {
				int[] s=list.get(i);
				visit[s[0]][s[1]]=true;
				go(s[0],s[1],max,1,false);
				visit[s[0]][s[1]]=false;
			}
			sb.append("#"+tc+" "+answer+"\n");
		}
		System.out.print(sb);
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static void go(int y,int x,int cur,int len,boolean use) {
		for(int i=0;i<4;i++) {
			int ny=y+dy[i];
			int nx=x+dx[i];
			if(ny>=0&&nx>=0&&ny<n&&nx<n&&!visit[ny][nx]) {
				int next=map[ny][nx];
				//그냥 이동 가능해서 그냥 이동하거나 공사하고 이동하거나.
				//이게 높이가 높을 수록 좋은 것임.
				//그래서 굳이 그냥 이동 가능한 데, 굳이 더 깎을 필요는 없음
				if(next<cur) {
					visit[ny][nx]=true;
					go(ny,nx,next,len+1,use);
					visit[ny][nx]=false;
				}else {
					if(!use) {
						int d=-1;
						for(int j=1;j<=k;j++) {
							if(next-j<cur) {
								d=j;
								break;
							}
						}
						//공사로는 해결할 수가 없으면 종료
						if(d==-1) {
							answer=answer<len?len:answer;
							continue;
						}else {
							visit[ny][nx]=true;
							go(ny,nx,next-d,len+1,true);
							visit[ny][nx]=false;
						}
					}else {
						//공사권도 없고 더 갈 수도 없는 상황
						answer=answer<len?len:answer;
						continue;
					}
				}
			}
		}
	}
}