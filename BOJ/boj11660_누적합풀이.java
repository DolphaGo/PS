import java.util.*;
import java.io.*;

public class boj11660_누적합풀이 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int map[][]=new int[n+1][n+1];
		for(int y=1;y<=n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=1;x<=n;x++) {
				int val=Integer.parseInt(st.nextToken());
				map[y][x]=map[y-1][x]+map[y][x-1]+val-map[y-1][x-1];
			}
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int y1=Integer.parseInt(st.nextToken());
			int x1=Integer.parseInt(st.nextToken());
			int y2=Integer.parseInt(st.nextToken());
			int x2=Integer.parseInt(st.nextToken());
			int res=map[y2][x2]-map[y2][x1-1]-map[y1-1][x2]+map[y1-1][x1-1];
			sb.append(res+"\n");
		}
		System.out.print(sb);
	}
}