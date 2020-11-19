import java.util.*;
import java.io.*;

public class boj14888 {
	static int n,max,min;
	static int arr[],state[],select[];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		arr=new int[n];
		select=new int[n-1];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());
		state=new int[4];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) state[i]=Integer.parseInt(st.nextToken());
		max=Integer.MIN_VALUE;
		min=Integer.MAX_VALUE;
		go(0);
		System.out.println(max+"\n"+min);
	}
	static void go(int v) {
		if(v==n-1) {
			int val=arr[0];
			for(int i=0;i<n-1;i++) {
				if(select[i]==0) val+=arr[i+1];
				else if(select[i]==1) val-=arr[i+1];
				else if(select[i]==2) val*=arr[i+1];
				else val/=arr[i+1];
			}
			max=max<val?val:max;
			min=min>val?val:min;
			return;
		}
		for(int i=0;i<4;i++) {
			if(state[i]>0) {
				--state[i];
				select[v]=i;
				go(v+1);
				++state[i];
			}
		}
	}
}