import java.util.*;
import java.io.*;

public class boj2293_0722 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] arr = new int[k + 1];
		arr[0]=1;
		for (int i = 0; i < n; i++) {
			int coin=Integer.parseInt(br.readLine());
			for(int j=coin;j<=k;j++) {
				arr[j]+=arr[j-coin];
			}
		}
		System.out.println(arr[k]);
	}
}