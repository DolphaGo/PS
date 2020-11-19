import java.io.*;
import java.util.*;

class swea1859 {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			long answer=0;
			int n=Integer.parseInt(br.readLine());
			int[] arr=new int[n];
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());
			
			int val=arr[n-1];
			for(int i=n-2;i>=0;i--) {
				if(val>=arr[i]) answer+=(val-arr[i]);
				else val=arr[i];
			}
			sb.append("#"+tc+" "+answer+"\n");
		}
		System.out.print(sb);
	}
}