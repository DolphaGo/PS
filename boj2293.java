import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int arr[] = new int[k + 1];
		arr[0]=1;
		for (int i = 0; i < n; i++) {
			int val = Integer.parseInt(br.readLine());
			for(int j=1;j<=k;j++) {
				if(j<val) continue;
				arr[j]+=arr[j-val];
			}
		}
		System.out.println(arr[k]);
	}
}