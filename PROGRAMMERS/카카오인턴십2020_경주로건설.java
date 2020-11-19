import java.util.*;
class Solution {
    public int solution(int[][] board) {
      int n=board.length;
		int[][] dist=new int[n][n];
		for(int y=0;y<n;y++) {
			Arrays.fill(dist[y], Integer.MAX_VALUE/2);
		}
		Queue<int[]> q=new LinkedList<>();
		//상,하,좌,우
		int dy[]= {-1,1,0,0};
		int dx[]= {0,0,-1,1};
		dist[0][0]=0;
    
		if(board[0][1]==0) {
			dist[0][1]=100;
			q.add(new int[] {0,1,3,100});
		}
		if(board[1][0]==0) {
			dist[1][0]=100;
			q.add(new int[] {1,0,1,100});
		}
		
		while(!q.isEmpty()) {
			int[] p=q.poll();
			for(int i=0;i<4;i++) {
				int ny=p[0]+dy[i];
				int nx=p[1]+dx[i];
				//범위 안에 있고, 갈 수 있을 때
				if(isRange(ny,nx,n)&&board[ny][nx]==0) {
					int cost=p[2]==i?100:600;
					if(dist[ny][nx]>=p[3]+cost) {
						dist[ny][nx]=p[3]+cost;
						q.add(new int[] {ny,nx,i,dist[ny][nx]});
					}
				}
			}
		}
		return dist[n-1][n-1];
	}
	static boolean isRange(int y,int x,int n) {
		return (y>=0&&x>=0&&y<n&&x<n);
	}
}
​