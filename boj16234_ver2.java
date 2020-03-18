import java.util.*;
import java.io.*;
public class Main {
	static int n,l,r;
	static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		l=Integer.parseInt(st.nextToken());
		r=Integer.parseInt(st.nextToken());
		map=new int[n][n];
		for(int y=0;y<n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<n;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		int answer=0;
		while(true) {
			if(hasUnion()) {
				++answer;
				while(!result.isEmpty()) {
					int[] p=result.poll();
					map[p[0]][p[1]]=p[2];
				}
			}else break;
		}
		System.out.println(answer);
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static Queue<int[]> unionSet=new LinkedList<int[]>();
	static Queue<int[]> result=new LinkedList<int[]>();
	
	static boolean hasUnion() {
		Queue<int[]> q=new LinkedList<int[]>();
		boolean visit[][]=new boolean[n][n];
		for(int y=0;y<n;y++) {
			for(int x=0;x<n;x++) {
				if(visit[y][x]) continue;
				visit[y][x]=true;
				q.add(new int[] {y,x});
				boolean flag=false;
				
				int sum=0;
				while(!q.isEmpty()) {
					int[] p=q.poll();
					int cur=map[p[0]][p[1]];
					for(int i=0;i<4;i++) {
						int ny=p[0]+dy[i];
						int nx=p[1]+dx[i];
						if(ny>=0&&nx>=0&&ny<n&&nx<n&&!visit[ny][nx]) {
							int next=map[ny][nx];
							int diff=Math.abs(cur-next);
							if(l<=diff&&diff<=r) {
								sum+=map[ny][nx];
								visit[ny][nx]=true;
								q.add(new int[] {ny,nx});
								unionSet.add(new int[] {ny,nx});
								if(!flag) flag=!flag;
							}
						}
					}
				}
				//연합이 가능할 때
				if(flag) {
					unionSet.add(new int[] {y,x});
					sum+=map[y][x];
					int num=unionSet.size();
					
					int newVal=sum/num;
					while(!unionSet.isEmpty()) {
						int[] p=unionSet.poll();
						result.add(new int[] {p[0],p[1],newVal});
					}
				}
			}
		}
		return result.size()==0?false:true;
	}
}
