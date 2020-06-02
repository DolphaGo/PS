import java.util.*;
import java.io.*;

public class boj17779_0602_BFS로색칠 {
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());
		int map[][]=new int[n+1][n+1];
		for(int y=1;y<=n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=1;x<=n;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		
		int box[][]=new int[n+1][n+1];
		int answer=Integer.MAX_VALUE;
		for(int d1=1;d1<=n;d1++) {
			for(int d2=1;d2<=n;d2++) {
				for(int y=1;y<=n;y++) {
					if(y+d1+d2>n) continue;
					for(int x=1;x<=n;x++) {
						if(x-d1<1||x+d2>n) continue;
						
						for(int k=1;k<=n;k++) Arrays.fill(box[k], 0);
						
						for(int i=0;i<=d1;i++) {
							box[y+i][x-i]=5;
							box[y+d2+i][x+d2-i]=5;
						}
						for(int i=0;i<=d2;i++) {
							box[y+i][x+i]=5;
							box[y+d1+i][x-d1+i]=5;
						}
						
						for(int yy=y-1;yy>=1;yy--) box[yy][x]=1;
						for(int xx=x+d2+1;xx<=n;xx++) box[y+d2][xx]=2;
						for(int xx=x-d1-1;xx>=1;xx--) box[y+d1][xx]=3;
						for(int yy=y+d1+d2+1;yy<=n;yy++) box[yy][x-d1+d2]=4;
						BFS(box,1,1,1);
						BFS(box,1,n,2);
						BFS(box,n,1,3);
						BFS(box,n,n,4);
						Marking(box,y,x,5);
						int arr[]=new int[5];
						for(int yy=1;yy<=n;yy++) {
							for(int xx=1;xx<=n;xx++) {
								int cur=box[yy][xx]-1;
								arr[cur]+=map[yy][xx];
							}
						}
						Arrays.sort(arr);
						answer=Math.min(answer,arr[4]-arr[0]);
					}
				}
			}
		}
		System.out.println(answer);
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static void BFS(int[][] box,int y,int x,int mark) {
		Queue<int[]> q=new LinkedList<int[]>();
		box[y][x]=mark;
		q.add(new int[] {y,x});
		while(!q.isEmpty()) {
			int[] p=q.poll();
			for(int i=0;i<4;i++) {
				int ny=p[0]+dy[i];
				int nx=p[1]+dx[i];
				if(ny>=1&&nx>=1&&ny<=n&&nx<=n&&box[ny][nx]==0) {
					box[ny][nx]=mark;
					q.add(new int[] {ny,nx});
				}
			}
		}
	}
	static void Marking(int[][] box,int y,int x,int mark) {
		for(int yy=1;yy<=n;yy++) {
			int idx=0;
			int arr[]=new int[2];
			for(int xx=1;xx<=n;xx++) {
				if(box[yy][xx]==mark) arr[idx++]=xx;
			}
			
			if(idx==2) {
				for(int xx=arr[0];xx<=arr[1];xx++) {
					box[yy][xx]=mark;
				}
			}
		}
	}
}