import java.io.*;
import java.util.*;

public class boj1019 {
	static long digit[]=new long[10];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m=1;
		int n=Integer.parseInt(br.readLine());
		int mul=0;
		while(m<=n) {
			if(m%10!=0) {
				toDigit(m++,pow(mul));
				continue;
			}
			if(n%10!=9) {
				toDigit(n--,pow(mul));
				continue;
			}
			if(m%10==0&&n%10==9) {
				n/=10;
				m/=10;
				long num=(n-m+1)*pow(mul++);
				for(int i=0;i<10;i++) digit[i]+=num;
			}
		}
		
		for(int i=0;i<10;i++) {
			System.out.print(digit[i]+" ");
		}
	}
	static void toDigit(int val,long w) {
		while(val>0) {
			digit[val%10]+=w;
			val/=10;
		}
	}
	static long pow(int i) {
		return i==0?1:10*pow(i-1);
	}
}