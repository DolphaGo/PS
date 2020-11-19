import java.util.*;
import java.io.*;

public class boj7682 {
	static char[][] map=new char[3][3];
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while(!(s=br.readLine()).equals("end")) {
			int cntO=0;
			int cntX=0;
			for(int y=0;y<3;y++) {
				for(int x=0;x<3;x++) {
					map[y][x]=s.charAt(3*y+x);
					if(map[y][x]=='O') ++cntO;
					else if(map[y][x]=='X') ++cntX;
				}
			}
			//1. 개수 오류 검사
			if(cntO>cntX||cntX>cntO+1) {
				invalid();
				continue;
			}
			boolean resO=check('O');
			boolean resX=check('X');
			//2. 최종 결과가 아닐 때 검사
			if(cntO+cntX<9) {
				if(!resO&&!resX) {
					invalid();
					continue;
				}
			}
			//3. 게임이 끝난 상황에서 더 수가 진행되었을 때.
			if(resO&&!resX) {
				if(cntX>cntO) {
					invalid();
					continue;
				}
			}else if(resX&&!resO) {
				if(cntO==cntX) {
					invalid();
					continue;
				}
			}else if(resX&&resO) {
				invalid();
				continue;
			}
			valid();
		}
		System.out.print(sb);
	}
	static boolean check(char tar) {
		for(int y=0;y<3;y++) if(map[y][0]==tar&&map[y][0]==map[y][1]&&map[y][1]==map[y][2]) return true;
		for(int x=0;x<3;x++) if(map[0][x]==tar&&map[0][x]==map[1][x]&&map[1][x]==map[2][x]) return true;
		if(map[0][0]==tar&&map[0][0]==map[1][1]&&map[1][1]==map[2][2]) return true;
		if(map[0][2]==tar&&map[0][2]==map[1][1]&&map[1][1]==map[2][0]) return true;
		return false;
	}
	
	static void invalid() {
		sb.append("invalid"+"\n");
	}
	static void valid() {
		sb.append("valid"+"\n");
	}
}