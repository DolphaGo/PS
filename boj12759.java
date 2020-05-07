import java.util.*;
import java.io.*;

public class boj12759 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int user=Integer.parseInt(br.readLine());
		int map[][]=new int[3][3];
		for(int i=0;i<9;i++) {
			st=new StringTokenizer(br.readLine());
			int y=Integer.parseInt(st.nextToken())-1;
			int x=Integer.parseInt(st.nextToken())-1;
			map[y][x]=user;
			
			int res=Test(map);
			if(res!=0) {
				System.out.println(res);
				return;
			}
			user=3-user;
		}
		System.out.println(0);
	}
	static int Test(int[][] map) {
		for(int y=0;y<3;y++) {
			if(map[y][0]!=0&&map[y][0]==map[y][1]&&map[y][1]==map[y][2]) return map[y][0];
		}
		for(int x=0;x<3;x++) {
			if(map[0][x]!=0&&map[0][x]==map[1][x]&&map[1][x]==map[2][x]) return map[0][x];
		}
		if(map[0][0]!=0&&map[0][0]==map[1][1]&&map[1][1]==map[2][2]) return map[0][0];
		if(map[0][2]!=0&&map[0][2]==map[1][1]&&map[1][1]==map[2][0]) return map[0][2];
		return 0;
	}
}