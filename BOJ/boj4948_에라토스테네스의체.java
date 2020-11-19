import java.util.*;
import java.io.*;

public class boj4948_에라토스테네스의체 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		boolean check[] = new boolean[123456 * 2 + 1];
		for (int i = 2; i < check.length; i++) {
			if (!check[i]) {
				for (int j = i+i; j < check.length; j += i) {
					check[j]=true;
				}
			}
		}
		
		int arr[]=new int[check.length];
		for(int i=2;i<check.length;i++) {
			if(!check[i]) arr[i]=1;
			arr[i]+=arr[i-1];
		}
		while (true) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0)
				break;
			int cnt = arr[n * 2] - arr[n];
			sb.append(cnt + "\n");
		}
		System.out.print(sb);
	}
}