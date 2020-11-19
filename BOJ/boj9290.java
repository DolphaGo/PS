import java.util.*;
import java.io.*;

public class boj9290 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		char[][] map=new char[3][3];
		StringBuilder sb=new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			for(int y=0;y<3;y++) {
				map[y]=br.readLine().toCharArray();
			}
			char me=br.readLine().charAt(0);
			loop:for(int y=0;y<3;y++) {
				for(int x=0;x<3;x++) {
					if(map[y][x]!='-') continue;
					map[y][x]=me;
					//내가 놓은 지점에서 가로,세로,대각선 확인
					boolean res=Test(map,y,x,me);
					if(!res) {
						map[y][x]='-';
					}else break loop;
				}
			}
			sb.append("Case "+tc+":"+"\n");
			for(int y=0;y<3;y++) {
				for(int x=0;x<3;x++) sb.append(map[y][x]);
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
	static boolean Test(char[][] map,int yy,int xx,char tar) {
		//놓은 지점 기준 세로 확인
		int cnt=0;
		for(int y=0;y<3;y++) {
			if(map[y][xx]!=tar) break;
			++cnt;
		}
		if(cnt==3) return true;
		//놓은 지점 기준 가로 확인
		cnt=0;
		for(int x=0;x<3;x++) {
			if(map[yy][x]!=tar) break;
			++cnt;
		}
		if(cnt==3) return true;
		//대각선 확인
		cnt=0;
		for(int y=0;y<3;y++) {
			if(map[y][y]!=tar) break;
			++cnt;
		}
		if(cnt==3) return true;
		cnt=0;
		for(int y=0;y<3;y++) {
			if(map[y][2-y]!=tar) break;
			++cnt;
		}
		if(cnt==3) return true;
		
		return false;
	}
}