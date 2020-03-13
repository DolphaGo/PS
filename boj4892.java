import java.util.*;
import java.io.*;

public class boj4892 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int tc=0;
		while(true) {
			++tc;
			int n0=Integer.parseInt(br.readLine());
			if(n0==0) break;
			int n1=3*n0;
			int n2=n1%2==0?n1/2:(n1+1)/2;
			int n3=3*n2;
			int n4=n3/9;
			String answer=n1%2==0?"even":"odd";
			sb.append(tc+". "+answer+" "+n4+"\n");
		}
		System.out.println(sb);
	}
}
