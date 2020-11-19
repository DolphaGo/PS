import java.io.*;
import java.util.*;

public class boj17822_0602 {
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int t=Integer.parseInt(st.nextToken());
		int arr[][]=new int[n+1][m];
		for(int y=1;y<=n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<m;x++) {
				arr[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		
		Deque<Integer> dq=new LinkedList<Integer>();
		
		for(int i=0;i<t;i++) {
			st=new StringTokenizer(br.readLine());
			int xi=Integer.parseInt(st.nextToken());
			int di=Integer.parseInt(st.nextToken());
			int ki=Integer.parseInt(st.nextToken());
			//번호가 xi의 배수인 원판을 di 방향으로 ki칸 돌리기
			
			for(int y=xi;y<=n;y+=xi) {
				switch(di) {
				case 0://시계방향으로 ki만큼
					for(int x=0;x<m;x++) dq.addLast(arr[y][x]);
					for(int k=0;k<ki;k++) dq.addFirst(dq.pollLast());
					for(int x=0;x<m;x++) arr[y][x]=dq.pollFirst();
					break;
				case 1://반시계방향으로 ki만큼
					for(int x=0;x<m;x++) dq.addLast(arr[y][x]);
					for(int k=0;k<ki;k++) dq.addLast(dq.pollFirst());
					for(int x=0;x<m;x++) arr[y][x]=dq.pollFirst();
					break;
				}
			}
			
			Queue<int[]> rm=new LinkedList<int[]>();//삭제 확정
			Queue<int[]> q=new LinkedList<int[]>();//BFS
			boolean visit[][]=new boolean[n+1][m];
			for(int y=1;y<=n;y++) {
				for(int x=0;x<m;x++) {
					if(arr[y][x]!=0&&!visit[y][x]) {
						boolean flag=false;
						visit[y][x]=true;
						q.add(new int[] {y,x});
						while(!q.isEmpty()) {
							int[] p=q.poll();
							for(int d=0;d<4;d++) {
								int ny=p[0]+dy[d];
								int nx=p[1]+dx[d];
								//원판은 범위 밖을 나갈 일이 없다.
								nx=(nx+m)%m;
								if(ny>=1&&ny<=n&&arr[ny][nx]==arr[y][x]&&!visit[ny][nx]) {
									visit[ny][nx]=true;
									if(!flag) {
										flag=true;
										rm.add(new int[] {y,x});
									}
									q.add(new int[] {ny,nx});
									rm.add(new int[] {ny,nx});
								}
							}
						}
					}
				}
			}
			
			if(rm.size()>0) {
				while(!rm.isEmpty()) {
					int[] p=rm.poll();
					arr[p[0]][p[1]]=0;
				}
			}else {
				int cnt=0;
				int sum=0;
				for(int y=1;y<=n;y++) {
					for(int x=0;x<m;x++) {
						if(arr[y][x]!=0) {
							sum+=arr[y][x];
							++cnt;
						}
					}
				}
				
				double avg=1.0*sum/cnt;
				
				for(int y=1;y<=n;y++) {
					for(int x=0;x<m;x++) {
						if(arr[y][x]!=0) {
							if(arr[y][x]>avg) arr[y][x]-=1;
							else if(arr[y][x]<avg) arr[y][x]+=1;
						}
					}
				}
			}
		}
		
		int answer=0;
		for(int y=1;y<=n;y++) {
			for(int x=0;x<m;x++) {
				answer+=arr[y][x];
			}
		}
		System.out.println(answer);
	}
}