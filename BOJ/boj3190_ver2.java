import java.util.*;
import java.io.*;

public class Main {
	static int dy[]= {-1,0,1,0};
	static int dx[]= {0,1,0,-1};
	static int N;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		int K=Integer.parseInt(br.readLine());
		map=new int[N][N];
		StringTokenizer st;
		for(int k=0;k<K;k++) {
			st=new StringTokenizer(br.readLine());
			int y=Integer.parseInt(st.nextToken())-1;
			int x=Integer.parseInt(st.nextToken())-1;
			map[y][x]=1;//사과
		}
		
		int dir=1;
		int time=0;
		Deque<int[]> snake=new LinkedList<int[]>();
		snake.add(new int[] {0,0});
		map[0][0]=2;//뱀 표현
		boolean flag=true;
		int L=Integer.parseInt(br.readLine());
		loop:for(int l=0;l<L;l++) {
			st=new StringTokenizer(br.readLine());
			int T=Integer.parseInt(st.nextToken());
			char C=st.nextToken().charAt(0);
			while(time<T) {
				++time;
				if(!go(snake,dir)) {
					flag=false;
					break loop;
				}
			}
			dir=C=='L'?(dir+3)%4:(dir+1)%4;
		}
		if(flag) {
			++time;
			while(go(snake,dir)) time++;
		}
		System.out.println(time);
	}
	static boolean go(Deque<int[]> snake,int dir) {
		int[] head=snake.peekFirst();
		int ny=head[0]+dy[dir];
		int nx=head[1]+dx[dir];
		//벽이나 자신의 몸에 부딪히면 종료
		if(ny<0||nx<0||ny==N||nx==N||map[ny][nx]==2) return false;
		if(map[ny][nx]==1) {//사과가 있으면
			//머리만 늘려주면 된다.
			snake.addFirst(new int[] {ny,nx});
			map[ny][nx]=2;
		}else {//빈 공간이라면
			snake.addFirst(new int[] {ny,nx}); //머리는 넣어주고
			map[ny][nx]=2;
			int[] last=snake.pollLast(); //꼬리는 지워주기
			map[last[0]][last[1]]=0;
		}
		
		return true;
	}
}
