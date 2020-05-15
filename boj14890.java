import java.util.*;
import java.io.*;

public class boj14890 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int L=Integer.parseInt(st.nextToken());
		
		int map[][]=new int[N][N];
		for(int y=0;y<N;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<N;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		int answer=0;
		
		boolean visit[][]=new boolean[N][N];
		//가로 검사
		for(int y=0;y<N;y++) {
			boolean flag=true;
			loop:for(int x=0;x<N-1;x++) {
				int prev=map[y][x];
				int next=map[y][x+1];
				if(prev==next) continue;
				if(prev==next+1) {
					for(int xx=x+1;xx<=x+L;xx++) {
						if(xx>=N||map[y][xx]!=next||visit[y][xx]) {
							flag=false;
							break loop;
						}else visit[y][xx]=true;
					}
				}else if(prev+1==next) {
					for(int xx=x-L+1;xx<=x;xx++) {
						if(xx<0||map[y][xx]!=prev||visit[y][xx]) {
							flag=false;
							break loop;
						}else visit[y][xx]=true;
					}
				}else {
					flag=false;
					break;
				}
			}
			if(flag) ++answer;
		}
		
		//방문맵 초기화
		for(int k=0;k<N;k++) Arrays.fill(visit[k], false);

		//세로 검사
		for(int x=0;x<N;x++) {
			boolean flag=true;
			loop:for(int y=0;y<N-1;y++) {
				int prev=map[y][x];
				int next=map[y+1][x];
				if(prev==next) continue;
				
				if(prev==next+1) {
					for(int yy=y+1;yy<=y+L;yy++) {
						if(yy>=N||map[yy][x]!=next) {
							flag=false;
							break loop;
						}else visit[yy][x]=true;
					}
				}else if(prev+1==next) {
					for(int yy=y-L+1;yy<=y;yy++) {
						if(yy<0||map[yy][x]!=prev||visit[yy][x]) {
							flag=false;
							break loop;
						}else visit[yy][x]=true;
					}
				}else {
					flag=false;
					break;
				}
			}
			if(flag) ++answer;
		}
		System.out.println(answer);
	}
}