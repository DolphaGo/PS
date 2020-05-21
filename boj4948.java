import java.util.*;
import java.io.*;

public class boj4948 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int arr[]=new int[123456*2+1];
		for(int i=2;i<arr.length;i++) {
			boolean flag=true;
			for(int j=2;j*j<=i;j++) {
				if(i%j==0) {
					flag=false;
					break;
				}
			}
			if(flag) arr[i]=arr[i-1]+1;
			else arr[i]=arr[i-1];
		}
		while(true) {
			int n=Integer.parseInt(br.readLine());
			if(n==0) break;
			int cnt=arr[n*2]-arr[n];
			sb.append(cnt+"\n");
		}
		System.out.print(sb);
	}
}