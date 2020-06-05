import java.io.*;
import java.util.*;

public class swea4008{
	static int n,max,min;
	static int com[],arr[],turn[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			int answer=0;
			n=Integer.parseInt(br.readLine());
			com=new int[4];
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<4;i++) {
				com[i]=Integer.parseInt(st.nextToken());
			} // + , -, *, / 순
			
			arr=new int[n];
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());
			
			max=Integer.MIN_VALUE;
			min=Integer.MAX_VALUE;
			turn=new int[n-1];
			go(0);
			answer=max-min;
			sb.append("#"+tc+" "+answer+"\n");
		}
		System.out.print(sb);
	}
	static void go(int v) {
		if(v==n-1) {
			int val=arr[0];
			for(int i=0;i<n-1;i++) {
				int c=turn[i];
				int nval=arr[i+1];
				if(c==0) val+=nval;
				else if(c==1) val-=nval;
				else if(c==2) val*=nval;
				else val/=nval;
			}
			max=max<val?val:max;
			min=min>val?val:min;
			return;
		}
		
		for(int i=0;i<4;i++) {
			if(com[i]>0) {
				--com[i];
				turn[v]=i;
				go(v+1);
				++com[i];
			}
		}
	}
}