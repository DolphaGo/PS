import java.util.*;
import java.io.*;

public class boj6588 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean primes[]=new boolean[1000001];
		for(int i=3;i<=1000000;i++) {
			int cnt=0;
			for(int j=2;j*j<=i;j++) {
				if(i%j==0) {
					++cnt;
					break;
				}
			}
			if(cnt==0) primes[i]=true;
		}
		StringBuilder sb=new StringBuilder();
		while(true) {
			int num=Integer.parseInt(br.readLine());
			if(num==0) break;
			boolean flag=false;
			for(int i=3;i<=num/2;i++) {
				if(!primes[i]) continue;
				if(primes[i]&&primes[num-i]) {
					flag=true;
					sb.append(num+" = "+i+" + "+(num-i)+"\n");
					break;
				}
			}
			if(!flag) sb.append("Goldbach's conjecture is wrong.\n");
		}
		System.out.println(sb);
	}
}
