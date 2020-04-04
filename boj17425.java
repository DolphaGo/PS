import java.io.*;
import java.util.*;

public class boj17425 {
	static final int max_d=19;// 2^k >= 500000 인 최소의 k
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m=Integer.parseInt(br.readLine());
		int f[][]=new int[m+1][max_d];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=1;i<=m;i++) f[i][0]=Integer.parseInt(st.nextToken());

		
		for(int j=1;j<max_d;j++) {
			for(int i=1;i<=m;i++) {
				f[i][j]=f[ f[i][j-1] ][j-1];
			}
		}
		
		int Q=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<Q;i++) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			
			//n =3 일때 
			for(int j=max_d-1;j>=0;j--) {
				if(n >= 1<<j) {
					n -= 1<<j;
					x = f[x][j];
				}
			}
			sb.append(x+"\n");
		}
		System.out.println(sb);
	}
}