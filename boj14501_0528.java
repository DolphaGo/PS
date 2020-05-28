import java.io.*;
import java.util.*;

public class boj14501_0528 {
	static int n,answer;
	static int arr[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		arr=new int[n+1][2];
		
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
		}
		answer=0;
		go(1,0);
		System.out.println(answer);
	}
	static void go(int v,int sum) {
		answer=answer<sum?sum:answer;
		if(v>n) return;
		
		if(v+arr[v][0]-1<=n) go(v+arr[v][0],sum+arr[v][1]);
		go(v+1,sum);
	}
}