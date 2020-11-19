import java.io.*;
import java.util.*;

public class boj14890_0528 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken());
		int L=Integer.parseInt(st.nextToken());
		
		int map[][]=new int[n][n];
		for(int y=0;y<n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<n;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		
		int answer=0;
		boolean visit[][]=new boolean[n][n];
		for(int y=0;y<n;y++) {
			int x=0;
			while(x<n-1) {
				if(map[y][x]==map[y][x+1]) ++x;
				else if(Math.abs(map[y][x]-map[y][x+1])==1) {
					if(map[y][x]>map[y][x+1]) {
						//공사 가능한지 확인
						boolean flag=true;
						for(int xx=x+1;xx<=x+L;xx++) {
							if(xx<n&&map[y][x+1]==map[y][xx]) continue;
							else {
								flag=false;
								break;
							}
						}
						
						if(flag) {
							//공사
							for(int xx=x+1;xx<=x+L;xx++) {
								visit[y][xx]=true;
							}
							x+=L;
						}else break;
					}else {
						boolean flag=true;
						//공사 가능 확인(+미리 공사가 되어 있는 것은 아닌지)
						for(int xx=x-L+1;xx<=x;xx++) {
							if(xx>=0&&map[y][xx]==map[y][x]&&!visit[y][xx]) continue;
							else {
								flag=false;
								break;
							}
						}
						
						if(flag) {
							for(int xx=x-L+1;xx<=x;xx++) {
								visit[y][xx]=true;
							}
							++x;
						}else break;
					}
				}else break;
			}
			if(x==n-1) ++answer;
		}
		
		visit=new boolean[n][n];
		for(int x=0;x<n;x++) {
			int y=0;
			while(y<n-1) {
				if(map[y][x]==map[y+1][x]) ++y;
				else if(Math.abs(map[y][x]-map[y+1][x])==1) {
					if(map[y][x]>map[y+1][x]) {
						//공사 가능한지 확인
						boolean flag=true;
						for(int yy=y+1;yy<=y+L;yy++) {
							if(yy<n&&map[y+1][x]==map[yy][x]) continue;
							else {
								flag=false;
								break;
							}
						}
						
						if(flag) {
							//공사
							for(int yy=y+1;yy<=y+L;yy++) {
								visit[yy][x]=true;
							}
							y+=L;
						}else break;
					}else {
						boolean flag=true;
						//공사 가능 확인(+미리 공사가 되어 있는 것은 아닌지)
						for(int yy=y-L+1;yy<=y;yy++) {
							if(yy>=0&&map[yy][x]==map[y][x]&&!visit[yy][x]) continue;
							else {
								flag=false;
								break;
							}
						}
						
						if(flag) {
							for(int yy=y-L+1;yy<=y;yy++) {
								visit[yy][x]=true;
							}
							++y;
						}else break;
					}
				}else break;
			}
			if(y==n-1) ++answer;
		}
		
		System.out.println(answer);
	}
}