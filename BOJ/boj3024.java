import java.util.*;
import java.io.*;

public class boj3024 {
	static int n;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());
		map=new char[n][n];
		for(int y=0;y<n;y++) {
			map[y]=br.readLine().toCharArray();
		}
		
		for(int y=0;y<n;y++) {
			for(int x=0;x<n;x++) {
				if(map[y][x]=='.') continue;
				//현재 위치 기준 오른쪽, 아래, 왼쪽 대각, 오른쪽 대각
				boolean res=Test(y,x);
				if(res) {
					System.out.println(map[y][x]);
					return;
				}
			}
		}
		System.out.println("ongoing");
	}
	static boolean Test(int yy,int xx) {
		char tar=map[yy][xx];
		//아래 검사
		int cnt=0;
		for(int y=yy+1;y<yy+3;y++) {
			if(y<n&&map[y][xx]==tar) ++cnt;
		}
		if(cnt==2) return true;
		//오른쪽 검사
		cnt=0;
		for(int x=xx+1;x<xx+3;x++) {
			if(x<n&&map[yy][x]==tar) ++cnt;
		}
		if(cnt==2) return true;
		//오른쪽 아래 대각선 검사
		cnt=0;
		for(int i=1;i<3;i++) {
			int ny=yy+i;
			int nx=xx+i;
			if(ny<n&&nx<n&&map[ny][nx]==tar) ++cnt;
		}
		if(cnt==2) return true;
		//왼쪽 아래 대각선 검사
		cnt=0;
		for(int i=1;i<3;i++) {
			int ny=yy+i;
			int nx=xx-i;
			if(ny<n&&nx>=0&&map[ny][nx]==tar) ++cnt;
		}
		if(cnt==2) return true;
		return false;
	}
}