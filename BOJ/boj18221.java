import java.io.*;
import java.util.*;

public class boj18221 {
	static int map[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		//A : 교수 , B : 성규
		int Ay=0,By=0,Ax=0,Bx=0;
		for(int y=0;y<n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<n;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
				if(map[y][x]==5) {
					Ay=y; Ax=x;
				}
				else if(map[y][x]==2) {
					By=y; Bx=x;
				}
			}
		}
		boolean flag=false;
		
		int yRange[]=new int[] {Ay<By?Ay:By,Ay<By?By:Ay};
		int xRange[]=new int[] {Ax<Bx?Ax:Bx,Ax<Bx?Bx:Ax};
		
		if(getdis(Ay,Ax,By,Bx)>=5) {
			flag=canEscap(yRange,xRange);
		}
		System.out.println(flag?1:0);
	}
	static double getdis(int a,int b,int c,int d) {
		return Math.sqrt(Math.pow(a-c, 2)+Math.pow(b-d, 2));
	}
	static boolean canEscap(int[] yR,int [] xR) {
		int cnt=0;
		for(int y=yR[0];y<=yR[1];y++) {
			for(int x=xR[0];x<=xR[1];x++) {
				if(map[y][x]==1) ++cnt;
				if(cnt==3) return true;
			}
		}
		return false;
	}
}