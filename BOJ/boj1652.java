import java.io.*;
import java.util.*;

public class boj1652 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		char map[][]=new char[n][n];
		for(int y=0;y<n;y++) {
			map[y]=br.readLine().toCharArray();
		}
		
		//가로 개수
		int cnt=0;
		for(int y=0;y<n;y++) {
			for(int x=0;x<n;x++) {
				if(map[y][x]=='.') {
					int space=1;
					while(x+1<n&&map[y][x+1]=='.') {
						x++;
						space++;
					}
					if(space>=2) ++cnt;
				}
			}
		}
		System.out.print(cnt+" ");
		//세로 개수
		cnt=0;
		for(int x=0;x<n;x++) {
			for(int y=0;y<n;y++) {
				if(map[y][x]=='.') {
					int space=1;
					while(y+1<n&&map[y+1][x]=='.') {
						y++;
						space++;
					}
					if(space>=2) ++cnt;
				}
			}
		}
		System.out.println(cnt);
	}
}