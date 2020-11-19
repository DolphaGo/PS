import java.io.*;
import java.util.*;

public class boj12100 {
	static int n,answer;
	static int map[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		for(int y=0;y<n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<n;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		//문제 : 최대 5번 이동시켜서 얻을 수 있는 가장 큰 수
		answer=Integer.MIN_VALUE;
		move(0);
		System.out.println(answer);
	}
	
	static void move(int cnt) {
		for(int y=0;y<n;y++) {
			for(int x=0;x<n;x++) {
				answer=answer<map[y][x]?map[y][x]:answer;
			}
		}
		if(cnt==5) return;

		for(int i=0;i<4;i++) {
			int[][] dupmap=new int[n][n];
			boolean[][] v=new boolean[n][n];
			for(int y=0;y<n;y++) dupmap[y]=map[y].clone();
			switch(i) {
			case 0:
				for(int y=1;y<n;y++) {
					for(int x=0;x<n;x++) {
						int ty=y;
						//우선 빈칸은 이동함
						while(ty-1>=0&&map[ty-1][x]==0) {
							int val=map[ty][x];
							map[ty-1][x]=val;
							map[ty--][x]=0;
						}
						if(ty-1>=0&&!v[ty-1][x]&&map[ty-1][x]==map[ty][x]) {
							map[ty-1][x]*=2;
							v[ty-1][x]=true;
							map[ty][x]=0;
						}
					}
				}
				break;
			case 1:
				for(int y=n-2;y>=0;y--) {
					for(int x=0;x<n;x++) {
						int ty=y;
						//우선 빈칸은 이동함
						while(ty+1<n&&map[ty+1][x]==0) {
							int val=map[ty][x];
							map[ty+1][x]=val;
							map[ty++][x]=0;
						}
						if(ty+1<n&&!v[ty+1][x]&&map[ty+1][x]==map[ty][x]) {
							map[ty+1][x]*=2;
							v[ty+1][x]=true;
							map[ty][x]=0;
						}
					}
				}
				break;
			case 2:
				for(int x=1;x<n;x++) {
					for(int y=0;y<n;y++) {
						int tx=x;
						//우선 빈칸은 이동함
						while(tx-1>=0&&map[y][tx-1]==0) {
							int val=map[y][tx];
							map[y][tx-1]=val;
							map[y][tx--]=0;
						}
						if(tx-1>=0&&!v[y][tx-1]&&map[y][tx-1]==map[y][tx]) {
							map[y][tx-1]*=2;
							v[y][tx-1]=true;
							map[y][tx]=0;
						}
					}
				}
				break;
			case 3:
				for(int x=n-2;x>=0;x--) {
					for(int y=0;y<n;y++) {
						int tx=x;
						//우선 빈칸은 이동함
						while(tx+1<n&&map[y][tx+1]==0) {
							int val=map[y][tx];
							map[y][tx+1]=val;
							map[y][tx++]=0;
						}
						if(tx+1<n&&!v[y][tx+1]&&map[y][tx+1]==map[y][tx]) {
							map[y][tx+1]*=2;
							v[y][tx+1]=true;
							map[y][tx]=0;
						}
					}
				}
				break;
			}
			move(cnt+1);
			for(int y=0;y<n;y++) map[y]=dupmap[y].clone();
		}
		
	}
}