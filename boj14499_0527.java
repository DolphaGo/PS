import java.io.*;
import java.util.*;

class boj14499_0527 {
	static int h,w;
	static int dy[]= {0,0,-1,1};
	static int dx[]= {1,-1,0,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h=Integer.parseInt(st.nextToken());
		w=Integer.parseInt(st.nextToken());
		int sy=Integer.parseInt(st.nextToken());
		int sx=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		int map[][]=new int[h][w];
		for(int y=0;y<h;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<w;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		int[][] z=new int[4][3];
		st=new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<k;i++) {
			int com=Integer.parseInt(st.nextToken())-1;
			int ny=sy+dy[com];
			int nx=sx+dx[com];
			if(ny>=0&&nx>=0&&ny<h&&nx<w) {
				//주사위를 굴리자.
				go(z,com);
				if(map[ny][nx]==0) {
					map[ny][nx]=z[3][1];
				}else {
					z[3][1]=map[ny][nx];
					map[ny][nx]=0;
				}
				sb.append(z[1][1]+"\n");
				sy=ny;
				sx=nx;
			}
		}
		System.out.print(sb);
	}
	static void go(int[][] z,int d) {
		int temp;
		switch(d) {
		case 0://동
			temp=z[1][1];
			z[1][1]=z[1][0];
			z[1][0]=z[3][1];
			z[3][1]=z[1][2];
			z[1][2]=temp;
			break;
		case 1://서
			temp=z[1][1];
			z[1][1]=z[1][2];
			z[1][2]=z[3][1];
			z[3][1]=z[1][0];
			z[1][0]=temp;
			break;
		case 2://북
			temp=z[1][1];
			z[1][1]=z[2][1];
			z[2][1]=z[3][1];
			z[3][1]=z[0][1];
			z[0][1]=temp;
			break;
		case 3://남
			temp=z[1][1];
			z[1][1]=z[0][1];
			z[0][1]=z[3][1];
			z[3][1]=z[2][1];
			z[2][1]=temp;
			break;
		}
	}
}