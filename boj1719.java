import java.io.*;
import java.util.*;
class boj1719{
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		
		int[][] dist=new int[n+1][n+1];
		int[][] ans=new int[n+1][n+1];
		for(int y=1;y<=n;y++) Arrays.fill(dist[y],Integer.MAX_VALUE/2);
		
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			dist[a][b]=dist[b][a]=c;
			ans[a][b]=b;
			ans[b][a]=a;
		}
		
		//Floyed-washall
		for(int k=1;k<=n;k++) {
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					if(dist[i][j]>dist[i][k]+dist[k][j]) {
						dist[i][j]=dist[i][k]+dist[k][j];
						ans[i][j]=ans[i][k];
					}
				}
			}
		}
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(i==j) sb.append("- ");
				else sb.append(ans[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}