import java.util.*;
import java.io.*;

public class Main {
	static int n,m,k;
	static int[][] add,map;
	static PriorityQueue<Integer>[][] tree;
	static int[] dy= {-1,-1,-1,0,1,1,1,0};
	static int[] dx= {-1,0,1,1,1,0,-1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		
		tree=new PriorityQueue[n][n];
		add=new int[n][n];
		map=new int[n][n];
		for(int y=0;y<n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<n;x++) {
				add[y][x]=Integer.parseInt(st.nextToken());
				tree[y][x]=new PriorityQueue<>();
				map[y][x]=5; //처음 모든 칸에 양분이 5가 있다.
			}
		}
		
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int y=Integer.parseInt(st.nextToken())-1;
			int x=Integer.parseInt(st.nextToken())-1;
			int z=Integer.parseInt(st.nextToken());
			tree[y][x].add(z);
		}
		
		Queue<int[]> dead=new LinkedList<int[]>();
		Queue<int[]> live=new LinkedList<int[]>();
		Queue<int[]> autumn=new LinkedList<int[]>();
		for(int t=1;t<=k;t++) {
			//봄
			for(int y=0;y<n;y++) {
				for(int x=0;x<n;x++) {
					if(tree[y][x].size()>0) {
						while(!tree[y][x].isEmpty()) {
							int p=tree[y][x].poll();
							if(map[y][x]>=p) {
								map[y][x]-=p;
								live.add(new int[] {y,x,p+1});
							}else dead.add(new int[] {y,x,p});
						}
					}
				}
			}
			//살은 것 처리
			while(!live.isEmpty()) {
				int[] p=live.poll();
				int y=p[0];
				int x=p[1];
				int age=p[2];
				tree[y][x].add(age);
			}
			
			//여름
			while(!dead.isEmpty()) {
				int[] p=dead.poll();
				int y=p[0];
				int x=p[1];
				int age=p[2];
				map[y][x]+=age/2;
			}
			
			//가을
			for(int y=0;y<n;y++) {
				for(int x=0;x<n;x++) {
					if(tree[y][x].size()>0) {
						Iterator<Integer> it=tree[y][x].iterator();
						while(it.hasNext()) {
							int val=it.next();
							if(val%5==0) {
								for(int i=0;i<8;i++) {
									int ny=y+dy[i];
									int nx=x+dx[i];
									if(isRange(ny,nx)) autumn.add(new int[] {ny,nx});
								}
							}
						}
					}
				}
			}
			
			while(!autumn.isEmpty()) {
				int[] p=autumn.poll();
				tree[p[0]][p[1]].add(1);
			}
			
			//겨울
			for(int y=0;y<n;y++) {
				for(int x=0;x<n;x++) {
					map[y][x]+=add[y][x];
				}
			}
		}
		int answer=0;
		for(int y=0;y<n;y++) {
			for(int x=0;x<n;x++) {
				answer+=tree[y][x].size();
			}
		}
		System.out.println(answer);
		
	}
	static boolean isRange(int y,int x) {
		return 0<=y&&y<n&&0<=x&&x<n;
	}
}