import java.io.*;
import java.util.*;

public class swea2382{
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		//미생물 처리용(Testcase가 있는 유형이기에, 미리 만들어두면 시간 단축에 도움)
		PriorityQueue<int[]> list[][]=new PriorityQueue[100][100];
		for(int y=0;y<100;y++) {
			for(int x=0;x<100;x++) {
				list[y][x]=new PriorityQueue<>(new Comparator<int[]>() {
					public int compare(int[] o1,int[] o2) {
						//미생물 양이 많은 순으로 정렬
						return Integer.compare(o2[0],o1[0]);
					}
				});
			}
		}

		for(int tc=1;tc<=T;tc++) {
			int answer=0;
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());
			Queue<int[]> q=new LinkedList<int[]>();
			for(int i=0;i<k;i++) {
				st=new StringTokenizer(br.readLine());
				int y=Integer.parseInt(st.nextToken());
				int x=Integer.parseInt(st.nextToken());
				int w=Integer.parseInt(st.nextToken());
				int d=Integer.parseInt(st.nextToken())-1;
				q.add(new int[] {y,x,w,d});
			}
			
			for(int i=1;i<=m;i++) {
				int size=q.size();
				for(int j=0;j<size;j++) {
					int[] p=q.poll();
					int y=p[0];	int x=p[1];	int w=p[2];	int d=p[3];
					int ny=y+dy[d]; int nx=x+dx[d];
					//가장 자리에 닿는다면
					if(ny==0||nx==0||ny==n-1||nx==n-1) {
						//미생물 수 반감 + 방향 전환
						w/=2;
						if(d<=1) d=1-d;
						else d=5-d;
					}
					list[ny][nx].add(new int[] {w,d});
				}
				
				for(int y=0;y<n;y++) {
					for(int x=0;x<n;x++) {
						if(list[y][x].size()>0) {
							//방향 고정
							int dir=list[y][x].peek()[1];
							int sum=0;
							while(!list[y][x].isEmpty()) {
								sum+=list[y][x].poll()[0];
							}
							q.add(new int[] {y,x,sum,dir});
						}
					}
				}
			}
			while(!q.isEmpty()) {
				answer+=q.poll()[2];
			}
			sb.append("#"+tc+" "+answer+"\n");
		}
		System.out.print(sb);
	}
}