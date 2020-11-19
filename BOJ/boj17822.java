import java.io.*;
import java.util.*;

public class boj17822 {
	static int n,m,T;
	static int disk[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		T=Integer.parseInt(st.nextToken());
		disk=new int[n+1][m];
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				disk[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for(int tc=0;tc<T;tc++) {
			st=new StringTokenizer(br.readLine());
			int xi=Integer.parseInt(st.nextToken());
			int di=Integer.parseInt(st.nextToken());
			int ki=Integer.parseInt(st.nextToken());
			ki%=m;
			for(int i=xi;i<=n;i+=xi) rotate(disk[i],di,ki);
			
			boolean res=bfs();
			if(!res) {
				double sum=0;
				int cnt=0;
				for(int y=1;y<=n;y++) {
					for(int x=0;x<m;x++) {
						if(disk[y][x]!=0) {
							sum+=disk[y][x];
							++cnt;
						}
					}
				}
				double avg=sum/cnt;
				
				for(int y=1;y<=n;y++) {
					for(int x=0;x<m;x++) {
						if(disk[y][x]==0) continue;
						if(disk[y][x]<avg) disk[y][x]+=1;
						else if(disk[y][x]>avg) disk[y][x]-=1;
					}
				}
			}
		}
		int answer=0;
		for(int y=1;y<=n;y++) {
			for(int x=0;x<m;x++) {
				answer+=disk[y][x];
			}
		}
		System.out.println(answer);
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	
	static boolean bfs() {
		Queue<int[]> q=new LinkedList<int[]>();
		Queue<int[]> list=new LinkedList<int[]>();
		boolean visit[][]=new boolean[n+1][m];
		boolean flag=false;
		for(int y=1;y<=n;y++) {
			for(int x=0;x<m;x++) {
				if(visit[y][x]||disk[y][x]==0) continue;
				visit[y][x]=true;
				q.add(new int[] {y,x,disk[y][x]});
				list.add(new int[] {y,x});
				while(!q.isEmpty()) {
					int[] p=q.poll();
					for(int i=0;i<4;i++) {
						int ny=p[0]+dy[i];
						int nx=p[1]+dx[i];
						nx+=m;
						nx%=m;
						if(ny>=1&&ny<=n&&nx>=0&&nx<m&&!visit[ny][nx]&&disk[ny][nx]==p[2]) {
							visit[ny][nx]=true;
							q.add(new int[] {ny,nx,p[2]});
							list.add(new int[] {ny,nx});
						}
					}
				}
				if(list.size()>=2) {
					flag=true;
					while(!list.isEmpty()) {
						int[] p=list.poll();
						disk[p[0]][p[1]]=0;
					}
				}
				list.clear();
			}
		}
		return flag;
	}
	
	
	static int[] rotate(int[] arr,int dir,int k) {
		Deque<Integer> q=new LinkedList<>();
		for(int i=0;i<arr.length;i++) q.add(arr[i]);
		switch(dir) {
		case 0:
			for(int i=0;i<k;i++) q.addFirst(q.pollLast());
			break;
		case 1:
			for(int i=0;i<k;i++) q.addLast(q.pollFirst());
			break;
		}
		for(int i=0;i<arr.length;i++) arr[i]=q.poll();
		return arr;
	}
}