import java.util.*;
import java.io.*;

public class boj19236 {
	static int dy[]= {-1,-1,0,1,1,1,0,-1};
	static int dx[]= {0,-1,-1,-1,0,1,1,1};
	static int answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int map[][]=new int[4][4];
		int dir[][]=new int[4][4];
		for(int i=0;i<4;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<4;j++) {
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken())-1;
				map[i][j]=a;
				dir[i][j]=b;
			}
		}
		int score=map[0][0];
		answer=0;
		map[0][0]=0;
		go(map,dir,0,0,dir[0][0],score);
		System.out.println(answer);
	}
	static void go(int[][] map,int[][] dir,int sy,int sx,int sd,int score) {
		//점수 최댓값 업데이트
		answer=answer<score?score:answer;
		int[][][] ret=move(map,dir,sy,sx);
		int[][] nmap=ret[0];
		int[][] ndir=ret[1];
		 
		for(int jump=1;jump<=3;jump++) {
			int ny=sy+jump*dy[sd];
			int nx=sx+jump*dx[sd];
			if(ny>=0&&nx>=0&&ny<4&&nx<4) {
				int val=nmap[ny][nx];
				//잡아먹을 수 있을 때
				if(val!=0) {
					nmap[ny][nx]=0;//잡아 먹음
					go(nmap,ndir,ny,nx,ndir[ny][nx],score+val);
					nmap[ny][nx]=val;
				}
			}
		}
	}
	static int[][][] move(int[][] map,int[][] dir,int sy,int sx){
		int[][][] ret=new int[2][4][4];
		for(int i=0;i<4;i++) {
			ret[0][i]=map[i].clone();
			ret[1][i]=dir[i].clone();
		}
		for(int num=1;num<=16;num++) {
			loop:for(int y=0;y<4;y++) {
				for(int x=0;x<4;x++) {
					if(ret[0][y][x]==num) {
						//기존방향~45도 돌리기
						int d=ret[1][y][x];
						for(int i=0;i<8;i++) {
							int ny=y+dy[(d+i)%8];
							int nx=x+dx[(d+i)%8];
							if(isRange(ny,nx,sy,sx)) {
								//다른 물고기가 있으면 위치 바꾸기
								if(ret[0][ny][nx]!=0) {
									//물고기 번호와 방향을 같이 바꾸기.
									for(int j=0;j<=1;j++) {
										int temp=ret[j][y][x];
										ret[j][y][x]=ret[j][ny][nx];
										ret[j][ny][nx]=temp;
									}
								}else {
									//물고기 이동
									for(int j=0;j<=1;j++) {
										ret[j][ny][nx]=ret[j][y][x];
										ret[j][y][x]=0;
									}
								}
								//방향 업데이트 해주기
								ret[1][ny][nx]=(d+i)%8;
								break loop;
							}
						}
					}
				}
			}
		}
		return ret;
	}
	//범위 내에 있고, 다음 위치가 상어가 아닐 때
	static boolean isRange(int y,int x,int sy,int sx) {
		if(y>=0&&x>=0&&y<4&&x<4&&!(y==sy&&x==sx)) return true;
		return false;
	}
}