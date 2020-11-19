import java.util.*;
import java.io.*;

public class boj17779_0607 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n=Integer.parseInt(br.readLine());
		int map[][]=new int[n][n];
		for(int y=0;y<n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<n;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		int answer=Integer.MAX_VALUE;
		for(int d1=1;d1<n;d1++) {
			for(int d2=1;d2<n;d2++) {
				for(int y=0;y<n-2;y++) {
					if(y+d1+d2>=n) continue;
					for(int x=0;x<n-1;x++) {
						if(x+d2>=n||x-d1<0) continue;
						
						int mask[][]=new int[n][n];
						for(int i=0;i<=d1;i++) {
							mask[y+i][x-i]=5;
							mask[y+d2+i][x+d2-i]=5;
						}
						for(int i=0;i<=d2;i++) {
							mask[y+i][x+i]=5;
							mask[y+d1+i][x-d1+i]=5;
						}
						
						for(int yy=y;yy<=y+d1+d2;yy++) {
							int cnt=0;
							int range[]=new int[2];
							for(int xx=x-d1;xx<=x+d2;xx++) {
								if(mask[yy][xx]==5) {
									range[cnt++]=xx;
								}
							}
							if(cnt==2) {
								for(int xx=range[0]+1;xx<range[1];xx++) {
									mask[yy][xx]=5;
								}
							}
						}
						
						for(int yy=0;yy<y+d1;yy++) {
							for(int xx=0;xx<=x;xx++) {
								if(mask[yy][xx]==0) mask[yy][xx]=1;
								else break;
							}
						}
						for(int yy=0;yy<=y+d2;yy++) {
							for(int xx=x+1;xx<n;xx++) {
								if(mask[yy][xx]==0) mask[yy][xx]=2;
							}
						}
						for(int yy=y+d1;yy<n;yy++) {
							for(int xx=0;xx<x-d1+d2;xx++) {
								if(mask[yy][xx]==0) mask[yy][xx]=3;
							}
						}
						for(int yy=y+d2+1;yy<n;yy++) {
							for(int xx=x-d1+d2;xx<n;xx++) {
								if(mask[yy][xx]==0) mask[yy][xx]=4;
							}
						}
						
						int arr[]=new int[5];
						for(int yy=0;yy<n;yy++) {
							for(int xx=0;xx<n;xx++) {
								int idx=mask[yy][xx]-1;
								arr[idx]+=map[yy][xx];
							}
						}
						Arrays.sort(arr);
						int diff=arr[4]-arr[0];
						answer=answer>diff?diff:answer;
					}
				}
			}
		}
		System.out.println(answer);
	}
}