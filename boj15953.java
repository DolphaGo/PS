import java.util.*;
import java.io.*;

public class boj15953 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		int A[]=new int[101];
		int B[]=new int[65];
		int idx=1;
		idx=Setting(A,idx,1,5000000);
		idx=Setting(A,idx,2,3000000);
		idx=Setting(A,idx,3,2000000);
		idx=Setting(A,idx,4,500000);
		idx=Setting(A,idx,5,300000);
		idx=Setting(A,idx,6,100000);
		
		idx=1;
		idx=Setting(B,idx,1,5120000);
		idx=Setting(B,idx,2,2560000);
		idx=Setting(B,idx,4,1280000);
		idx=Setting(B,idx,8,640000);
		idx=Setting(B,idx,16,320000);
		
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
}