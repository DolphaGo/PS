import java.util.*;
import java.io.*;

public class boj15953_0512 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		int idx=1;
		int A[]=new int[101];
		int Amoney[]=new int[] {5000000,3000000,2000000,500000,300000,100000};
		for(int i=0;i<Amoney.length;i++) idx=Setting(A,idx,i+1,Amoney[i]);
		
		idx=1;
		int B[]=new int[65];
		int Bmoney[]=new int[] {5120000,2560000,1280000,640000,320000};
		for(int i=0;i<Bmoney.length;i++) idx=Setting(B,idx, pow(2, i),Bmoney[i]);
		
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			sb.append((A[a]+B[b])+"\n");
		}
		System.out.print(sb);
	}
	static int Setting(int[] arr,int idx,int len,int money) {
		for(int i=idx;i<idx+len;i++) arr[i]=money;
		return idx+len;
	}
	static int pow(int a,int b) {
		return b==0?1:a*pow(a,b-1);
	}
}