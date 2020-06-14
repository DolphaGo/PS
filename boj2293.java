import java.util.*;
import java.io.*;

public class boj2293 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int v[] = new int[n];
		int arr[] = new int[k + 1];
		for (int i = 0; i < n; i++) {
			v[i] = Integer.parseInt(br.readLine());
		}
		for (int j = 0; j < n; j++) {
			for (int i = 1; i <= k; i++) {
				if (i < v[j]) continue;
				if(i==v[j]) {
					++arr[i];
					continue;
				}else arr[i]+=arr[i-v[j]];
			}
		}
		System.out.println(arr[k]);
	}
}