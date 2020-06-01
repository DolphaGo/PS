import java.util.*;
import java.io.*;

public class boj17779_0602 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n + 1][n + 1];

		for (int y = 1; y <= n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 1; x <= n; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = Integer.MAX_VALUE;
		int arr[] = new int[5];
		int box[][]=new int[n+1][n+1];
		for (int d1 = 1; d1 <= n; d1++) {
			for (int d2 = 1; d2 <= n; d2++) {
				for (int y = 1; y <= n; y++) {
					if(y+d1+d2>n) continue;
					for (int x = 1; x <= n; x++) {
						if(1>x-d1 || x+d2>n ) continue;

						Arrays.fill(arr, 0);
						for(int k=1;k<=n;k++) Arrays.fill(box[k],0);						
						for(int i=0;i<=d1;i++) {
							box[y+i][x-i]=5;
							box[y+d2+i][x+d2-i]=5;
						}
						for(int i=0;i<=d2;i++) {
							box[y+i][x+i]=5;
							box[y+d1+i][x-d1+i]=5;
						}
						
						int tmp[]=new int[2];
						for(int yy=1;yy<=n;yy++) {
							int idx=0;
							for(int xx=1;xx<=n;xx++) {
								if(box[yy][xx]==5) {
									tmp[idx++]=xx;
								}
							}
							//내부 Fill
							if(idx==2) {
								for(int xx=tmp[0];xx<=tmp[1];xx++) box[yy][xx]=5;
							}
						}
					
						for(int r=1;r<y+d1;r++) {
							for(int c=1;c<=x;c++) {
								if(box[r][c]==0) {
									box[r][c]=1;
								}
							}
						}
						
						for(int r=1;r<=y+d2;r++) {
							for(int c=x+1;c<=n;c++) {
								if(box[r][c]==0) {
									box[r][c]=2;
								}
							}
						}
						
						for(int r=y+d1;r<=n;r++) {
							for(int c=1;c<x-d1+d2;c++) {
								if(box[r][c]==0) {
									box[r][c]=3;
								}
							}
						}
						
						for(int r=y+d2+1;r<=n;r++) {
							for(int c=x-d1+d2;c<=n;c++) {
								if(box[r][c]==0) {
									box[r][c]=4;
								}
							}
						}
						
						for(int yy=1;yy<=n;yy++) {
							for(int xx=1;xx<=n;xx++) {
								int cur=box[yy][xx]-1;
								arr[cur]+=map[yy][xx];
							}
						}
						Arrays.sort(arr);
						answer = Math.min(answer, arr[4] - arr[0]);
					}
				}
			}
		}
		System.out.println(answer);
	}
}