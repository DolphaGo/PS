import java.util.*;
import java.io.*;
public class boj2422_ver2 {
	static int n,m,answer;
	static boolean check[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		check=new boolean[n+1][n+1];
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			check[a][b]=true;
			check[b][a]=true;
		}
		answer=0;
		go(0,0);
		System.out.println(answer);
	}
	static int temp[]=new int[3];
	static void go(int v,int cnt) {
		if(cnt==3) {
			boolean flag=true;

			if(check[temp[0]][temp[1]]||check[temp[1]][temp[2]]||check[temp[2]][temp[0]]) flag=false;
			if(flag) ++answer;
			return;
		}
		if(v==n) return;
		temp[cnt]=v+1;
		go(v+1,cnt+1);
		go(v+1,cnt);
	}
}
