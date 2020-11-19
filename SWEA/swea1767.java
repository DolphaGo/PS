import java.util.*;
import java.io.*;
public class Solution {
	static int n,answer,cmax;
	static int map[][];
	static ArrayList<int[]> list;
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			n=Integer.parseInt(br.readLine());
			map=new int[n][n];
			list=new ArrayList<int[]>();
			for(int y=0;y<n;y++) {
				st=new StringTokenizer(br.readLine());
				for(int x=0;x<n;x++) {
					map[y][x]=Integer.parseInt(st.nextToken());
					if(map[y][x]==1&&!side(y,x)) list.add(new int[] {y,x});
				}
			}
			answer=Integer.MAX_VALUE;
			cmax=Integer.MIN_VALUE;
			go(0,0,0);
			System.out.println("#"+tc+" "+answer);
		}
	}
	
	static void go(int v,int select,int len) {
		
		if(select>cmax) {
			cmax=select;
			answer=len;
		}else if(select==cmax) answer=answer>len?len:answer;
		
		if(v==list.size()) return;
		Queue<int[]> q=new LinkedList<int[]>();
		int cur[]=list.get(v);
		for(int i=0;i<4;i++) {
			int ny=cur[0]+dy[i];
			int nx=cur[1]+dx[i];
			//이 방향으로 쭉 진행해 나가서
			//벽면에 닿을 수 있는지 부터 검사.
			while(ny>=0&&nx>=0&&ny<n&&nx<n&&map[ny][nx]==0) {
				q.add(new int[] {ny,nx});
				map[ny][nx]=2;
				ny+=dy[i];
				nx+=dx[i];
			}
			
			//전원 연결이 가능할 때
			if(ny<0||nx<0||ny==n||nx==n) {
				go(v+1,select+1,len+q.size());
			}
			
			//원래로 돌려놓기
			while(!q.isEmpty()) {
				int[] p=q.poll();
				map[p[0]][p[1]]=0;
			}
			
		}
		//연결하지 않고 다음 프로세서로 이동하기
		go(v+1,select,len);
	}
	
	static boolean side(int y,int x) {
		if(y==n-1||x==n-1||y==0||x==0) return true;
		else return false;
	}
}
