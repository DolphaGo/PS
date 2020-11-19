import java.io.*;
import java.util.*;

class swea1954 {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		
		StringBuilder sb=new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			int n=Integer.parseInt(br.readLine());
			sb.append("#"+tc+"\n");
			int[][] arr=new int[n][n];
			int num=1;
			int y=0,x=0;
			while(num<=n*n) {
				while(x<n&&arr[y][x]==0) arr[y][x++]=num++;
				--x; ++y;
				while(y<n&&arr[y][x]==0) arr[y++][x]=num++;
				--y; --x;
				while(x>=0&&arr[y][x]==0) arr[y][x--]=num++;
				++x; --y;
				while(y>=0&&arr[y][x]==0) arr[y--][x]=num++;
				++y; ++x;
			}
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					sb.append(arr[i][j]+" ");
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}
}