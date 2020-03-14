import java.util.*;
import java.io.*;

public class boj1644 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n=Integer.parseInt(br.readLine());
		long sum[]=new long[n];
		int idx=1;
		for(int i=2;i<=n;i++) {
			boolean flag=true;
			for(int j=2;j*j<=i;j++) {
				if(i%j==0) {
					flag=false;
					break;
				}
			}
			if(flag) {
				sum[idx]=sum[idx-1]+i;
				idx++;
			}
		}
		int answer=0;
		for(int i=1;i<idx;i++) {
			if(sum[i]<n) continue;
			int start=0;
			int end=i;
			while(start<=end) {
				int mid=(start+end)/2;
				if(sum[i]-sum[mid]>n) {
					start=mid+1;
				}else if(sum[i]-sum[mid]<n) {
					end=mid-1;
				}else {
					++answer;
					break;
				}
			}
		}
		System.out.println(answer);
	}
}
