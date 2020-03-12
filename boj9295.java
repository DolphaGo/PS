import java.util.*;
import java.io.*;

public class boj9295 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		int a,b,sum;
		StringBuilder sb=new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			sum=a+b;
			sb.append("Case "+tc+": "+sum+"\n");
		}
		System.out.println(sb);
	}
}
