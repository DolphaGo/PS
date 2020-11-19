import java.util.*;
import java.io.*;

public class boj2294 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coin=new int[n];
		int[] arr = new int[k + 1];
		Arrays.fill(arr, Integer.MAX_VALUE/2);
		for (int i = 0; i < n; i++) {
			coin[i]=Integer.parseInt(br.readLine());
		}
		
		for(int i=1;i<=k;i++) {
			for(int j=0;j<n;j++) {
				if(i-coin[j]<0) continue;
				arr[coin[j]]=1;
				if(arr[i]>arr[i-coin[j]]+1) {
					arr[i]=arr[i-coin[j]]+1;
				}
			}
		}
		System.out.println(arr[k]==Integer.MAX_VALUE/2?-1:arr[k]);
	}
}