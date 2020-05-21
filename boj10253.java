import java.util.*;
import java.io.*;

public class boj10253 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			long a=Long.parseLong(st.nextToken());
			long b=Long.parseLong(st.nextToken());
			while(true) {
				if(a==1) {
					sb.append(b+"\n");
					break;
				}
				long res=b/a;
				long tmp=res+1;
				a=a*tmp-b;
				b=b*tmp;
				long gcd=gcd(a,b);
				a/=gcd;
				b/=gcd;
			}
		}
		System.out.print(sb);
	}
	static long gcd(long a,long b) {
		return b==0?a:gcd(b,a%b);
	}
}