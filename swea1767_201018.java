import java.util.*;
import java.io.*;

public class swea1767_201018 {
	static int n,answer,max;
	static List<int[]> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		list=new ArrayList<int[]>();
		for(int tc=1;tc<=T;tc++) {
			answer=0;
			n=Integer.parseInt(br.readLine());
			int[][] map=new int[n][n];
			int connect=0;
			for(int y=0;y<n;y++) {
				st=new StringTokenizer(br.readLine());
				for(int x=0;x<n;x++) {
					map[y][x]=Integer.parseInt(st.nextToken());
					if((y==0||y==n-1||x==0||x==n-1)&&map[y][x]==1) ++connect;
					else if(map[y][x]==1) {
						list.add(new int[] {y,x});
					}
				}
			}
			
			max=0;
			answer=Integer.MAX_VALUE;
			go(map,0,connect,0);
			sb.append("#"+tc+" "+answer).append("\n");
			list.clear();
		}
		System.out.print(sb);
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static void go(int[][] map,int v,int connect,int len) {
		if(max<connect) {
			max=connect;
			answer=len;
		}else if(max==connect) {
			answer=Math.min(answer, len);
		}
		
		if(v==list.size()) return;
		
		int[] cur=list.get(v);
		for(int i=0;i<4;i++) {
			int[][] temp=new int[n][n];
			for(int y=0;y<n;y++) {
				for(int x=0;x<n;x++) {
					temp[y][x]=map[y][x];
				}
			}
			int l=0;
			int ny=cur[0]+dy[i];
			int nx=cur[1]+dx[i];
			while(isRange(ny,nx)&&temp[ny][nx]==0) {
				++l;
				temp[ny][nx]=1;
				ny+=dy[i];
				nx+=dx[i];
			}
			if(!isRange(ny,nx)) {
				go(temp,v+1,connect+1,len+l); //연결하고 넘어가기
			}
		}
		
		go(map,v+1,connect,len); //연결 안하고 그냥 넘어가기
		
	}
	static boolean isRange(int y,int x) {
		return 0<=y&&y<n&&0<=x&&x<n;
	}
}