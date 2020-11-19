import java.io.*;
import java.util.*;

public class boj18809 {
	static int n,m,g,r,answer;
	static int[][] map;
	static ArrayList<int[]> list=new ArrayList<int[]>();
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		g=Integer.parseInt(st.nextToken());
		r=Integer.parseInt(st.nextToken());
		map=new int[n][m];
		for(int y=0;y<n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<m;x++) {
				//0 : 호수, 1: 배양X , 2: 배양O
				map[y][x]=Integer.parseInt(st.nextToken());
				//배양액을 뿌릴 수 있는 곳의 위치를 모아둠.
				if(map[y][x]==2) {
					map[y][x]=1;
					list.add(new int[] {y,x});
				}
			}
		}
		answer=Integer.MIN_VALUE;
		go(0);
		System.out.println(answer);
	}
	static ArrayList<int[]> selected=new ArrayList<int[]>();
	static void go(int v) {
		if(selected.size()==g+r) {
			for(int i=0;i<1<<(g+r);i++) {
				int red=0;
				int green=0;
				for(int j=0;j<g+r;j++) {
					if((i&(1<<j))>0) ++red;
					else ++green;
				}
				if(red==r&&green==g) {
					int[][] temp=new int[n][m];
					for(int y=0;y<n;y++) temp[y]=map[y].clone();
					
					for(int j=0;j<g+r;j++) {
						//red = 3 , green = 4 로 표현(배양액 뿌리기)
						int[] loc=selected.get(j);
						temp[loc[0]][loc[1]]=(i&(1<<j))>0?3:4;
					}
					answer=Math.max(answer,test(temp));
				}
			}
			return;
		}
		if(v==list.size()) return;
		
		selected.add(list.get(v));
		go(v+1);
		selected.remove(selected.size()-1);
		
		go(v+1);
	}
	static int test(int[][] temp) {
		Queue<int[]> q=new LinkedList<int[]>();
		for(int y=0;y<n;y++) {
			for(int x=0;x<m;x++) {
				if(temp[y][x]==3) {
					temp[y][x]=-1;
					q.add(new int[] {y,x,3});
				}
				else if(temp[y][x]==4) {
					temp[y][x]=-1;
					q.add(new int[] {y,x,4});
				}
			}
		}
		int flower=0;
		//꽃이 필 수 있는 가능성이 있는 곳 저장.
		ArrayList<int[]> wait=new ArrayList<int[]>();
		while(!q.isEmpty()) {
			int size=q.size(); 
			for(int iter=0;iter<size;iter++) {
				int[] p=q.poll();
				for(int i=0;i<4;i++) {
					int ny=p[0]+dy[i];
					int nx=p[1]+dx[i];
					if(Range(ny,nx)&&temp[ny][nx]>0&&temp[ny][nx]!=p[2]) {
						if(temp[ny][nx]==1) {
							temp[ny][nx]=p[2];
							wait.add(new int[] {ny,nx,p[2]});
						}else temp[ny][nx]=-2; //꽃이 피었다.
					}
				}
			}
			for(int[] w:wait) {
				if(temp[w[0]][w[1]]==-2) ++flower;
				else {
					temp[w[0]][w[1]]=-1;
					q.add(w);
				}
			}
			wait.clear();
		}
		
		return flower;
	}
	static boolean Range(int y,int x) {
		if(y>=0&&x>=0&&y<n&&x<m) return true;
		return false;
	}
}