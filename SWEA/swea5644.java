import java.util.*;
import java.io.*;
public class swea5644 {
	static ArrayList<Integer> map[][];
	static int answer;
	static int p[];
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		
		int dy[]= {0,-1,0,1,0};
		int dx[]= {0,0,1,0,-1};
		map=new ArrayList[10][10];
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				map[i][j]=new ArrayList<>();
			}
		}
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			int M=Integer.parseInt(st.nextToken());
			int A=Integer.parseInt(st.nextToken());
			
			int Amove[]=new int[M];
			int Bmove[]=new int[M];
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<M;i++) Amove[i]=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<M;i++) Bmove[i]=Integer.parseInt(st.nextToken());
			
			p=new int[A+1];
			for(int i=1;i<=A;i++) {
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken())-1;
				int y=Integer.parseInt(st.nextToken())-1;
				int c=Integer.parseInt(st.nextToken());
				p[i]=Integer.parseInt(st.nextToken());
				makemap(y,x,c,i);
			}
			
			int Ay=0; int Ax=0;
			int By=9; int Bx=9;
			answer=0;
			getMax(Ay,Ax,By,Bx);
			for(int i=0;i<M;i++) {
				Ay+=dy[Amove[i]];Ax+=dx[Amove[i]];
				By+=dy[Bmove[i]];Bx+=dx[Bmove[i]];
				getMax(Ay,Ax,By,Bx);
			}
			System.out.println("#"+tc+" "+answer);
			
			for(int i=0;i<10;i++) {
				for(int j=0;j<10;j++) {
					map[i][j].clear();
				}
			}
			
		}
	}
	static void getMax(int ay,int ax,int by,int bx) {
		//우선 A와 B가 같은 영역 내에 있는지 확인
		if(map[ay][ax].size()>0&&map[by][bx].size()>0) {
			int max=Integer.MIN_VALUE;
			for(int val:map[ay][ax]) {
				int e1=p[val];
				for(int val2:map[by][bx]) {
					if(val==val2) max=Math.max(max, p[val]);
					else max=Math.max(max, e1+p[val2]);
				}
			}
			answer+=max;
		}else if(map[ay][ax].size()>0) {
			int max=Integer.MIN_VALUE;
			for(int val:map[ay][ax]) {
				max=Math.max(max, p[val]);
			}
			answer+=max;
		}else if(map[by][bx].size()>0) {
			int max=Integer.MIN_VALUE;
			for(int val:map[by][bx]) {
				max=Math.max(max, p[val]);
			}
			answer+=max;
		}else return;
		
	}
	static void makemap(int y,int x,int c,int pidx) {
		int dy[]= {-1,1,0,0};
		int dx[]= {0,0,-1,1};
		Queue<int[]> q=new LinkedList<int[]>();
		boolean visit[][]=new boolean[10][10];
		visit[y][x]=true;
		map[y][x].add(pidx);
		q.add(new int[] {y,x,0});
		while(!q.isEmpty()) {
			int[] poll=q.poll();
			for(int i=0;i<4;i++) {
				int ny=poll[0]+dy[i];
				int nx=poll[1]+dx[i];
				if(ny>=0&&nx>=0&&ny<10&&nx<10&&!visit[ny][nx]) {
					visit[ny][nx]=true;
					map[ny][nx].add(pidx);
					if(poll[2]+1<c) q.add(new int[] {ny,nx,poll[2]+1});
				}
			}
		}
	}
}
