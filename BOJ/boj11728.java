import java.util.*;
import java.io.*;

public class boj11728 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int[] A=new int[n];
		int[] B=new int[m];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) A[i]=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) B[i]=Integer.parseInt(st.nextToken());
		StringBuilder sb=new StringBuilder();
		int aidx=0;
		int bidx=0;
		while(aidx<n&&bidx<m) {
			int aval=A[aidx];
			int bval=B[bidx];
			if(aval<bval) {
				sb.append(aval+" ");
				++aidx;
			}else{
				sb.append(bval+" ");
				++bidx;
			}
		}
		if(aidx==n&&bidx<m) {
			while(bidx<m) sb.append(B[bidx++]+" ");
		}else if(bidx==m&&aidx<n) {
			while(aidx<n) sb.append(A[aidx++]+" ");
		}
		System.out.println(sb);
	}
}
