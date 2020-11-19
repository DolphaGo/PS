import java.io.*;
import java.util.*;

public class boj14888_0528 {
	static int n,max,min;
	static int[] com,arr,list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		n=Integer.parseInt(br.readLine());
		arr=new int[n];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		com=new int[4];
		for(int i=0;i<4;i++) com[i]=Integer.parseInt(st.nextToken());
		
		max=Integer.MIN_VALUE;
		min=Integer.MAX_VALUE;
		list=new int[n-1];
		go(0);
		System.out.println(max+"\n"+min);
	}
	static void go(int v) {
		if(v==n-1) {
			int val=arr[0];
			for(int i=0;i<n-1;i++) {
				int nval=arr[i+1];
				int c=list[i];
				switch(c) {
				case 0:
					val+=nval;
					break;
				case 1:
					val-=nval;
					break;
				case 2:
					val*=nval;
					break;
				case 3:
					val/=nval;
					break;
				}
			}
			max=val>max?val:max;
			min=val<min?val:min;
			return;
		}
		
		for(int i=0;i<4;i++) {
			if(com[i]>0) {
				--com[i];
				list[v]=i;
				go(v+1);
				++com[i];
			}
		}
	}
}