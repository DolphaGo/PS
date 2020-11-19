import java.util.*;
import java.io.*;
public class boj15658 {
	static int n;
	static int arr[],li[];
	static int m[];
	static int max,min;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		arr=new int[n];
		li=new int[n-1];
		m=new int[4];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			m[i]=Integer.parseInt(st.nextToken());
		}
		
		max=Integer.MIN_VALUE;
		min=Integer.MAX_VALUE;
		go(0);
		System.out.println(max);
		System.out.println(min);
	}
	static void go(int v) {
		if(v==n-1) {
			int val=arr[0];
			for(int i=0;i<n-1;i++) {
				int val2=arr[i+1];
				// +
				if(li[i]==0) {
					val+=val2;
				// -
				}else if(li[i]==1) {
					val-=val2;
				// *
				}else if(li[i]==2) {
					val*=val2;
				// /
				}else {
					val/=val2;
				}
			}
			max=Math.max(val,max);
			min=Math.min(val,min);
			return;
		}
		
		for(int i=0;i<4;i++) {
			if(m[i]>0) {
				--m[i];
				li[v]=i;
				go(v+1);
				++m[i];
			}
		}
	}
}
