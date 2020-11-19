import java.io.*;
import java.util.*;

class swea5653 {
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		//비활성
		Queue<int[]> off=new LinkedList<int[]>();
		//활성
		Queue<int[]> on=new LinkedList<int[]>();
		//o[2] : 시간 , o[3] : 생존력
		PriorityQueue<int[]> spread=new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				return Integer.compare(o2[3],o1[3]);
			}
		});
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			int answer=0;
			
			st=new StringTokenizer(br.readLine());
			int h=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());
			boolean visit[][]=new boolean[1000][1000];
			
			for(int y=0;y<h;y++) {
				st=new StringTokenizer(br.readLine());
				for(int x=0;x<w;x++) {
					int val=Integer.parseInt(st.nextToken());
					if(val>0) {
						off.add(new int[] {y+500,x+500,val,val});
						visit[y+500][x+500]=true; //확정
					}
				}
			}
			int t=0;
			
			//on의 p[2]는 활성화 유지 시간(~초까지 활성상태),
			//off의 p[2]는 비활성화 유지 시간(~초까지 비활성상태)이다.
			while(++t<=k) {
				int size=on.size();
				for(int i=0;i<size;i++) {
					int[] p=on.poll();
					for(int j=0;j<4;j++) {
						int ny=p[0]+dy[j];
						int nx=p[1]+dx[j];
						if(!visit[ny][nx]) {
							spread.add(new int[] {ny,nx,t+p[3],p[3]});
						}
					}
					if(p[2]>t) on.add(p);
				}
				
				while(!spread.isEmpty()) {
					int[] p=spread.poll();
					if(!visit[p[0]][p[1]]) {
						visit[p[0]][p[1]]=true;
						off.add(p);
					}
				}
				
				size=off.size();
				for(int i=0;i<size;i++) {
					int[] p=off.poll();
					if(p[2]<=t) {
						p[2]+=p[3];
						on.add(p);
					}
					else off.add(p); 
				}
			}

			answer=on.size()+off.size(); //(활성 + 비활성)
			sb.append("#"+tc+" "+answer+"\n");
			on.clear();
			off.clear();
		}
		System.out.print(sb);
	}
}