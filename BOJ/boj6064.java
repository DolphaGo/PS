import java.io.*;
import java.util.*;

public class boj6064 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			int max=lcm(M,N);
			while(true) {
				if(x>max||(x-1)%N+1==y) break;
				x+=M;
			}
			System.out.println(x>max?-1:x);
		}	
	}
	static int gcd(int a,int b) {
		return b==0?a:gcd(b,a%b);
	}
	static int lcm(int a,int b) {
		return a*b/gcd(a,b);
	}
}
