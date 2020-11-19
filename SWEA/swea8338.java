import java.io.*;
import java.util.*;

class swea8338 {
	static int n,answer;
	static int[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			n=Integer.parseInt(br.readLine());
			arr=new int[n];
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());
			
			answer=Integer.MIN_VALUE;
			go(1,arr[0]);
			sb.append("#"+tc+" "+answer+"\n");
		}
		System.out.print(sb);
	}
	static void go(int v,int val) {
		if(v==n) {
			answer=answer<val?val:answer;
			return;
		}
		go(v+1,val+arr[v]);
		go(v+1,val*arr[v]);
	}
}