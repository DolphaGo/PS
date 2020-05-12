import java.util.*;
import java.io.*;

public class boj17136 {
	static int answer,one;
	static int map[][]=new int[10][10];
	static int res[]=new int[] {5,5,5,5,5,5};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		one=0;
		for(int i=0;i<10;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<10;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) ++one;
			}
		}
		answer=Integer.MAX_VALUE;
		go(0,0,0);
		System.out.println(answer==Integer.MAX_VALUE?-1:answer);
	}
	static void go(int sy,int use,int clean) {
		if(use>=answer) return;
		if(clean==one) {
			answer=use;
			return;
		}
		for(int y=sy;y<10;y++) {
			for(int x=0;x<10;x++) {
				if(map[y][x]==0) continue;
				
				for(int size=1;size<=5;size++) {
					if(res[size]==0) continue;
					
					boolean flag=true;
					loop:for(int yy=y;yy<y+size;yy++) {
						for(int xx=x;xx<x+size;xx++) {
							if(yy>=10||xx>=10||map[yy][xx]==0) {
								flag=false;
								break loop;
							}
						}
					}
					if(flag) {
						for(int yy=y;yy<y+size;yy++) {
							for(int xx=x;xx<x+size;xx++) {
								map[yy][xx]=0;
							}
						}
						--res[size];
						go(y,use+1,clean+size*size);
						++res[size];
						for(int yy=y;yy<y+size;yy++) {
							for(int xx=x;xx<x+size;xx++) {
								map[yy][xx]=1;
							}
						}
					}
				}
				return;
			}
		}
	}
}