import java.util.*;
import java.io.*;

public class boj14501 {
	static int[][] a;
	static int n,answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		a=new int[n+1][2];
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			a[i][0]=Integer.parseInt(st.nextToken());
			a[i][1]=Integer.parseInt(st.nextToken());
		}
		answer=0;
		go(1,0);
		System.out.println(answer);
	}
	static void go(int v,int sum) {
		answer=Math.max(answer,sum);
		//v날 일을 할 경우의 돈
		//(일을 할 수 있어야 하는지 선 검사가 필요함)
		if(v>n) return;
		if(v+a[v][0]-1<=n) go(v+a[v][0],sum+a[v][1]);
		//일을 안하고 다음 날로 넘어갈 때
		if(v+1<=n) go(v+1,sum);
	}
}
