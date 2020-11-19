import java.util.*;
import java.io.*;

public class boj17779 {
	static int n;
	static int map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		map = new int[n + 1][n + 1];

		for (int y = 1; y <= n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 1; x <= n; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = Integer.MAX_VALUE;
		int arr[] = new int[5];
		boolean visit[][]=new boolean[n+1][n+1];

		
	
		for (int d1 = 1; d1 <= n; d1++) {
			for (int d2 = 1; d2 <= n; d2++) {
				for (int y = 1; y <= n; y++) {
					if(y+d1+d2>n) break;
					for (int x = 1; x <= n; x++) {
						if(1>x-d1) continue;
						if(x+d2>n) break;

						Arrays.fill(arr, 0);
						for(int k=1;k<=n;k++) Arrays.fill(visit[k], false);
						
						for(int i=0;i<=d1;i++) {
							visit[y+i][x-i]=true;
							visit[y+d2+i][x+d2-i]=true;
						}
						
						for(int i=0;i<=d2;i++) {
							visit[y+i][x+i]=true;
							visit[y+d1+i][x-d1+i]=true;
						}
						
						for(int r=y+1;r<y+d1+d2;r++) {
							boolean flag=false;
							for(int c=1;c<=n;c++) {
								if(!flag&&visit[r][c]) {
									arr[4]+=map[r][c];
									flag=true;
								}
								else if(flag&&!visit[r][c]) {
									arr[4]+=map[r][c];
									visit[r][c]=true;
								}
								else if(flag&&visit[r][c]) {
									arr[4]+=map[r][c];
									break;
								}
							}
						}
						arr[4]+=map[y][x];
						arr[4]+=map[y+d1+d2][x-d1+d2];
						
						
						for(int r=1;r<y+d1;r++) {
							for(int c=1;c<=x;c++) {
								if(!visit[r][c]) arr[0]+=map[r][c];
							}
						}
						
						for(int r=1;r<=y+d2;r++) {
							for(int c=x+1;c<=n;c++) {
								if(!visit[r][c]) arr[1]+=map[r][c];
							}
						}
						
						for(int r=y+d1;r<=n;r++) {
							for(int c=1;c<x-d1+d2;c++) {
								if(!visit[r][c]) arr[2]+=map[r][c];
							}
						}
						
						for(int r=y+d2+1;r<=n;r++) {
							for(int c=x-d1+d2;c<=n;c++) {
								if(!visit[r][c]) arr[3]+=map[r][c];
							}
						}
						Arrays.sort(arr);
						int res = arr[4] - arr[0];
						answer = Math.min(answer, res);
					}
				}
			}
		}

		System.out.println(answer);
	}
}
