import java.io.*;
import java.util.*;
class boj16234_0601 {
	static int n,l,r;
	static int map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
			if(go()) {
				++answer;
			}else break;
		}
		System.out.println(answer);
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static boolean go() {
		boolean flag=false;
		boolean visit[][]=new boolean[n][n];
		Queue<int[]> q=new LinkedList<>();
		ArrayList<int[]> list=new ArrayList<>();
		int cnt=0;
		for(int y=0;y<n;y++) {
			for(int x=0;x<n;x++) {
				if(!visit[y][x]) {
					visit[y][x]=true;
					q.add(new int[] {y,x});
					list.add(new int[] {y,x,++cnt});
					while(!q.isEmpty()) {
						int[] p=q.poll();
						for(int i=0;i<4;i++) {
							int ny=p[0]+dy[i];
							int nx=p[1]+dx[i];
							if(ny>=0&&nx>=0&&ny<n&&nx<n&&Range(map[p[0]][p[1]],map[ny][nx])&&!visit[ny][nx]) {
								visit[ny][nx]=true;
								q.add(new int[] {ny,nx});
								list.add(new int[] {ny,nx,cnt});
							}
						}
					}
				}
			}
		}
		//cnt가 맵의 크기보다 작으면 연합이 있다는 뜻이니까.
		if(cnt<n*n) flag=true;
		
		int arr[]=new int[cnt+1];
		int count[]=new int[cnt+1];
		for(int i=0;i<list.size();i++) {
			count[list.get(i)[2]]++;
			arr[list.get(i)[2]]+=map[list.get(i)[0]][list.get(i)[1]];
		}
		
		for(int i=0;i<list.size();i++) {
			int y=list.get(i)[0];
			int x=list.get(i)[1];
			int idx=list.get(i)[2];
			map[y][x]=arr[idx]/count[idx];
		}
		return flag;
	}
	static boolean Range(int a,int b) {
		int diff=Math.abs(a-b);
		if(l<=diff&&diff<=r) return true;
		return false;
	}
}