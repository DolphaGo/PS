import java.util.*;
import java.io.*;

public class boj1199 {
	static int n,all;
	static StringBuilder answer=new StringBuilder();
	static int[][] arr;
	static int[] visit,degree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());
		arr=new int[n+1][n+1];
		degree=new int[n+1];
		all=0;
		
		for(int y=1;y<=n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=1;x<=n;x++) {
				int num=Integer.parseInt(st.nextToken());
				arr[y][x]=num;
				
				all+=num;
				
				degree[y]+=num;
				degree[x]+=num;
			}
		}
		all/=2;
		visit=new int[all+1];
		boolean Euler=true;
		
		for(int i=1;i<=n;i++) {
			degree[i]/=2;
			if(degree[i]%2!=0) {
				Euler=false;
				break;
			}
		}
		
		if(!Euler) {
			System.out.println(-1);
		}else {
			visit[0]=1;
			go(1,1,1);
			System.out.println(answer);
		}
	}
	
	static void go(int v,int s,int start) {
		if(s==all+1) {
			if(v==1) {
				for(int i=0;i<=all;i++) answer.append(visit[i]+" ");
				System.out.println(answer);
				System.exit(0);
			}
			return;
		}
		for(int i=1;i<=n;i++) {
			if(arr[v][i]!=0) {
				--arr[v][i];
				--arr[i][v];
				visit[s]=i;
				go(i,s+1,start);
				++arr[i][v];
				++arr[v][i];
			}
		}
		
	}
}