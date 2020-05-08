import java.util.*;
import java.io.*;

public class boj2609 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int a=Integer.parseInt(st.nextToken());
		int b=Integer.parseInt(st.nextToken());
		int gcd=gcd(a,b);
		int lcm=a*b/gcd;
		System.out.println(gcd+"\n"+lcm);
	}
	static int gcd(int a,int b) {
		return b==0?a:gcd(b,a%b);
	}
}