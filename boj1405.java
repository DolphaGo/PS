import java.util.*;
import java.io.*;

public class boj1405 {
	static int n;
	static double p[];
	static int dy[]= {0,0,1,-1};
	static int dx[]= {1,-1,0,0};
	static boolean map[][];
	static double answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		p=new double[4];
		//동,서,남,북
		for(int i=0;i<4;i++) {
			p[i]=Integer.parseInt(st.nextToken())*0.01;
		}
		//0부터 28까지 접근 가능
		//중간은 14,14
		map=new boolean[29][29];
		answer=0;
		map[14][14]=true;
		go(14,14,1.0,0);
		System.out.printf("%.10f",answer);
	}
	
	static void go(int y,int x,double res,int cnt) {
		if(cnt==n) {
			answer+=res;
			return;
		}
		for(int i=0;i<4;i++) {
			int ny=y+dy[i];
			int nx=x+dx[i];
			if(ny>=29||nx>=29||ny<0||nx<0) continue;
			
			//지금까지 방문하지 않았던 곳들 중에 방문
			if(!map[ny][nx]) {
				map[ny][nx]=true;
				go(ny,nx,res*p[i],cnt+1);
				map[ny][nx]=false;
			}
		}
	}
}