import java.io.*;
import java.util.*;

public class boj9020 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean primes[]=new boolean[10001];
		for(int i=2;i<=10000;i++) {
			boolean flag=true;
			for(int j=2;j*j<=i;j++) {
				if(i%j==0) {
					flag=false;
					break;
				}
			}
			if(flag) primes[i]=true;
		}
		
		StringBuilder sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			int n=Integer.parseInt(br.readLine());
			int first=n/2;
			while(true) {
				if(primes[first]&&primes[n-first]) {
					sb.append(first+" "+(n-first)+"\n");
					break;
				}
				--first;
			}
		}
		System.out.println(sb.toString());
	}
}