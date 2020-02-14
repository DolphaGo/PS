import java.io.*;
import java.util.*;

public class boj1850 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a=Long.parseLong(st.nextToken());
		long b=Long.parseLong(st.nextToken());
		long max=a<b?b:a;
		long min=a<b?a:b;
		System.out.println(getanswer(gcd(max,min)));
	}
	static String getanswer(long val) {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<val;i++) sb.append('1');
		return sb.toString();
	}
	static long gcd(long a,long b) {
		return b==0? a:gcd(b,a%b);
	}
}