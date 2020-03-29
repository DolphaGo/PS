import java.io.*;
import java.util.*;

public class boj18808 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		boolean note[][]=new boolean[n][m];
		int answer=0;
		for(int i=0;i<k;i++) {
			st=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int[][] sticker=new int[r][c];
			for(int j=0;j<r;j++) {
				st=new StringTokenizer(br.readLine());
				for(int z=0;z<c;z++) {
					sticker[j][z]=Integer.parseInt(st.nextToken());
				}
			}
			
			loop2:for(int ro=0;ro<4;ro++) {
				int h=sticker.length;
				int w=sticker[0].length;
				for(int y=0;y<=n-h;y++) {
					for(int x=0;x<=m-w;x++) {
						boolean flag=true;
						loop:for(int yi=y;yi<y+h;yi++) {
							for(int xi=x;xi<x+w;xi++) {
								if(note[yi][xi]&&sticker[yi-y][xi-x]==1) {
									flag=false;
									break loop;
								}
							}
						}
						if(flag) {
							for(int yi=y;yi<y+h;yi++) {
								for(int xi=x;xi<x+w;xi++) {
									if(!note[yi][xi]&&sticker[yi-y][xi-x]==1) {
										note[yi][xi]=true;
										++answer;
									}
								}
							}
							break loop2;
						}
					}
				}
				sticker=rotate(sticker);
			}
		}
	
		System.out.println(answer);
	}
	static int[][] rotate(int[][] sticker) {
		int h=sticker.length;
		int w=sticker[0].length;
		int newSticker[][]=new int[w][h];
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				newSticker[x][h-1-y]=sticker[y][x];
			}
		}
		return newSticker;
	}
}