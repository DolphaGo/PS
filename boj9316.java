import java.util.*;
import java.io.*;

public class boj9316 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<=n;i++) {
			sb.append("Hello World, Judge ");
			sb.append(i+"!\n");
		}
		System.out.println(sb);
	}
}
