import java.io.*;
import java.util.*;
class boj14938{
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int r=Integer.parseInt(st.nextToken());
		int[] item=new int[n+1];
		st=new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) item[i]=Integer.parseInt(st.nextToken());
		
		int[][] dist=new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE/2);
			dist[i][i]=0;
		}
		for(int i=0;i<r;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			dist[a][b]=dist[b][a]=c;
		}
		
		for(int k=1;k<=n;k++) {
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					if(dist[i][j]>dist[i][k]+dist[k][j]) {
						dist[i][j]=dist[i][k]+dist[k][j];
					}
				}
			}
		}
		int max=0;
		for(int i=1;i<=n;i++) {
			int sum=0;
			for(int j=1;j<=n;j++) {
				if(dist[i][j]<=m) sum+=item[j];
			}
			max=max<sum?sum:max;
		}
		System.out.println(max);
	}
}