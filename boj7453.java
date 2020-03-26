import java.io.*;
import java.util.*;

public class Main {
	static long[] list;
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		n=Integer.parseInt(br.readLine());
		long[][] map=new long[n][4];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<4;j++) {
				map[i][j]=Long.parseLong(st.nextToken());
			}
		}
		int idx=0;
		list=new long[n*n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				list[idx++]=map[i][2]+map[j][3];
			}
		}
		Arrays.sort(list);
		
		long answer=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				long val=map[i][0]+map[j][1];
				//정확히 0이 되어야 하니까 -val을 찾아야함.
				int lo=lower_bound(-val);
				int up=upper_bound(-val);
				if(lo<n*n&&up<=n*n&&list[lo]==-val) answer+=(up-lo);
			}
		}
		System.out.println(answer);
	}
	static int lower_bound(long val) {
		int s=0;
		int e=n*n;
		while(s<e) {
			int mid=(s+e)/2;
			if(list[mid]<val) s=mid+1;
			else e=mid;
		}
		return e;
	}
	static int upper_bound(long val) {
		int s=0;
		int e=n*n;
		while(s<e){
			int mid=(s+e)/2;
			if(list[mid]<=val) s=mid+1;
			else e=mid;
		}
		return e;
	}
}