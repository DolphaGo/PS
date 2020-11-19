import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		char[] command=br.readLine().toCharArray();
		int dy[]= {-1,0,1,0};//0 : 북, 1 : 동 , 2 :남 , 3 :서
		int dx[]= {0,1,0,-1};
		int dir=2;
		char map[][]=new char[101][101];
		int cy=50;
		int cx=50;
		int miny,minx,maxy,maxx;
		maxx=minx=cx;
		maxy=miny=cy;
		map[cy][cx]='.';
		for(int i=0;i<n;i++) {
			switch(command[i]) {
			case 'R':
				dir=dir+1==4?0:dir+1;
				break;
			case 'L':
				dir=dir-1==-1?3:dir-1;
				break;
			case 'F':
				cy+=dy[dir];
				cx+=dx[dir];
				map[cy][cx]='.';
				miny=miny>cy?cy:miny;
				minx=minx>cx?cx:minx;
				maxy=maxy<cy?cy:maxy;
				maxx=maxx<cx?cx:maxx;
				break;
			}
		}
		
		StringBuilder sb=new StringBuilder();
		for(int y=miny;y<=maxy;y++) {
			for(int x=minx;x<=maxx;x++) {
				if(map[y][x]=='.') sb.append('.');
				else sb.append('#');
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
