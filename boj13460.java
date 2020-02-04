import java.io.*;
import java.util.*;

public class boj13460 {
	static int by, bx, ry, rx, h, w;
	static boolean rflag, bflag;
	static int answer;
	static char map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new char[h][w];
		for (int y = 0; y < h; y++) {
			map[y] = br.readLine().toCharArray();
			for (int x = 0; x < w; x++) {
				if (map[y][x] == 'B') {
					by = y;
					bx = x;
				} else if (map[y][x] == 'R') {
					ry = y;
					rx = x;
				}
			}
		}
		rflag = false;
		bflag = false;
		answer = Integer.MAX_VALUE;
		dfs(-1,0);
		if (answer == Integer.MAX_VALUE)
			answer = -1;
		System.out.println(answer);
	}
	
	static void dfs(int before,int cnt) {
		if(cnt>9) return;
		if(cnt>=answer) return;
		if(bflag) return;
		for(int i=0;i<4;i++) {
			if(before==i) continue;
			//현재 상태 가져오기. (백트래킹용)
			int cry=ry;	int crx=rx;	int cby=by;	int cbx=bx;
			boolean crflag=rflag; boolean cbflag=bflag;
			char[][] dummy=new char[h][w];
			for(int y=0;y<h;y++) dummy[y]=map[y].clone();
			
			move(i);
			
			if(rflag&&!bflag) 
				answer=answer>cnt+1?cnt+1:answer;
			
			dfs(i,cnt+1);

			for(int y=0;y<h;y++) map[y]=dummy[y].clone();
			ry=cry;rx=crx;by=cby;bx=cbx;
			rflag=crflag;bflag=cbflag;
		}
	}
	
	//상하좌우 순
	static void move(int dir) {
//		System.out.println("현재 방향 : "+dir);
//		System.out.println("현재 맵");
//		for(int y=0;y<h;y++) {
//			for(int x=0;x<w;x++) {
//				System.out.print(map[y][x]);
//			}
//			System.out.println();
//		}
//		
		switch(dir) {
		case 0:
			if(ry<by) { 
				//빨간공 이동
				while(ry-1>=0&&map[ry-1][rx]=='.') {
					map[ry][rx]='.';
					--ry;
					map[ry][rx]='R';
				}
				if(ry-1>=0&&map[ry-1][rx]=='O') {
					rflag=true;
					map[ry][rx]='.';
				}
				
				//파란공 이동
				while(by-1>=0&&map[by-1][bx]=='.') {
					map[by][bx]='.';
					map[--by][bx]='B';
				}
				if(by-1>=0&&map[by-1][bx]=='O') {
					bflag=true;
					map[by][bx]='.';
				}
				
			}else {
				//파란공 이동
				while(by-1>=0&&map[by-1][bx]=='.') {
					map[by][bx]='.';
					map[--by][bx]='B';
				}
				if(by-1>=0&&map[by-1][bx]=='O') {
					bflag=true;
					map[by][bx]='.';
				}
				
				//빨간공 이동
				while(ry-1>=0&&map[ry-1][rx]=='.') {
					map[ry][rx]='.';
					--ry;
					map[ry][rx]='R';
				}
				if(ry-1>=0&&map[ry-1][rx]=='O') {
					rflag=true;
					map[ry][rx]='.';
				}
				
			}
			break;
		case 1:
			if(ry>by) {//빨간공 먼저
				//빨간공 이동
				while(ry+1<h&&map[ry+1][rx]=='.') {
					map[ry][rx]='.';
					map[++ry][rx]='R';
				}
				if(ry+1<h&&map[ry+1][rx]=='O') {
					rflag=true;
					map[ry][rx]='.';
				}
				
				//파란공 이동
				while(by+1<h&&map[by+1][bx]=='.') {
					map[by][bx]='.';
					map[++by][bx]='B';
				}
				if(by+1<h&&map[by+1][bx]=='O') {
					bflag=true;
					map[by][bx]='.';
				}
				
				
			}else {
				//파란공 이동
				while(by+1<h&&map[by+1][bx]=='.') {
					map[by][bx]='.';
					map[++by][bx]='B';
				}
				if(by+1<h&&map[by+1][bx]=='O') {
					bflag=true;
					map[by][bx]='.';
				}
				
				//빨간공 이동
				while(ry+1<h&&map[ry+1][rx]=='.') {
					map[ry][rx]='.';
					map[++ry][rx]='R';
				}
				if(ry+1<h&&map[ry+1][rx]=='O') {
					rflag=true;
					map[ry][rx]='.';
				}
				
			}
			break;
		case 2:
			if(rx<bx) {//빨간공 먼저
				//빨간공 이동
				while(rx-1>=0&&map[ry][rx-1]=='.') {
					map[ry][rx]='.';
					map[ry][--rx]='R';
				}
				if(rx-1>=0&&map[ry][rx-1]=='O') {
					rflag=true;
					map[ry][rx]='.';
				}
				
				//파란공 이동
				while(bx-1>=0&&map[by][bx-1]=='.') {
					map[by][bx]='.';
					map[by][--bx]='B';
				}
				if(bx-1>=0&&map[by][bx-1]=='O') {
					bflag=true;
					map[by][bx]='.';
				}
			}else {
				//파란공 이동
				while(bx-1>=0&&map[by][bx-1]=='.') {
					map[by][bx]='.';
					map[by][--bx]='B';
				}
				if(bx-1>=0&&map[by][bx-1]=='O') {
					bflag=true;
					map[by][bx]='.';
				}
				//빨간공 이동
				while(rx-1>=0&&map[ry][rx-1]=='.') {
					map[ry][rx]='.';
					map[ry][--rx]='R';
				}
				if(rx-1>=0&&map[ry][rx-1]=='O') {
					rflag=true;
					map[ry][rx]='.';
				}
			}
			break;
		case 3:
			if(rx>bx) { //빨간공 먼저
				//빨간공 이동
				while(rx+1<w&&map[ry][rx+1]=='.') {
					map[ry][rx]='.';
					map[ry][++rx]='R';
				}
				if(rx+1<w&&map[ry][rx+1]=='O') {
					rflag=true;
					map[ry][rx]='.';
				}
				
				//파란공 이동
				while(bx+1<w&&map[by][bx+1]=='.') {
					map[by][bx]='.';
					map[by][++bx]='B';
				}
				if(bx+1<w&&map[by][bx+1]=='O') {
					bflag=true;
					map[by][bx]='.';
				}
			}else {
				//파란공 이동
				while(bx+1<w&&map[by][bx+1]=='.') {
					map[by][bx]='.';
					map[by][++bx]='B';
				}
				if(bx+1<w&&map[by][bx+1]=='O') {
					bflag=true;
					map[by][bx]='.';
				}
				//빨간공 이동
				while(rx+1<w&&map[ry][rx+1]=='.') {
					map[ry][rx]='.';
					map[ry][++rx]='R';
				}
				if(rx+1<w&&map[ry][rx+1]=='O') {
					rflag=true;
					map[ry][rx]='.';
				}
			}
			break;
		}
		
//		System.out.println("=======이동 후=======");
//		for(int y=0;y<h;y++) {
//			for(int x=0;x<w;x++) {
//				System.out.print(map[y][x]);
//			}
//			System.out.println();
//		}
	}
}