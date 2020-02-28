import java.io.*;
import java.util.*;

public class boj2115 {
	static int dy[]={1,-1,0,0};
	static int dx[]={0,0,1,-1};
	
	static int h,w,answer=0;
	static char[][] map;
	static boolean[][][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		h=Integer.parseInt(st.nextToken());
		w=Integer.parseInt(st.nextToken());
		map=new char[h][w];
		visit=new boolean[4][h][w];
		for(int y=0;y<h;y++) {
			map[y]=br.readLine().toCharArray();
		}
		for(int y=1;y<h-1;y++) {
			for(int x=1;x<w-1;x++) {
				if(map[y][x]=='.') {
					for(int i=0;i<4;i+=2) {
						int ny=y+dy[i];
						int nx=x+dx[i];
						if(map[ny][nx]=='.') {
							if(i==0) {
								//아래 확인 => 1.벽인지? 2.채워졌는지? 좌/우
								for(int j=2;j<4;j++) {
									int check_y1=y+dy[j];
									int check_y2=ny+dy[j];
									int check_x1=x+dx[j];
									int check_x2=nx+dx[j];
									if(map[check_y1][check_x1]=='X'&&map[check_y2][check_x2]=='X'&&!visit[j][y][x]&&!visit[j][ny][nx]) {
										visit[j][y][x]=visit[j][ny][nx]=true;
										answer++;
									}
								}
							}else {
								//오른쪽 확인 => 벽은 위/아래
								for(int j=0;j<2;j++) {
									int check_y1=y+dy[j];
									int check_y2=ny+dy[j];
									int check_x1=x+dx[j];
									int check_x2=nx+dx[j];
									if(map[check_y1][check_x1]=='X'&&map[check_y2][check_x2]=='X'&&!visit[j][y][x]&&!visit[j][ny][nx]) {
										visit[j][y][x]=visit[j][ny][nx]=true;
										answer++;
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println(answer);
	}
}
