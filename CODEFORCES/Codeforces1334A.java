import java.util.*;
import java.io.*;

public class Codeforces1334A {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			int n=Integer.parseInt(br.readLine());
			int a=0;
			int b=0;
			boolean flag=true;
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				int aa=Integer.parseInt(st.nextToken());
				int bb=Integer.parseInt(st.nextToken());
				if(!(a<=aa&&b<=bb&&(bb-b)<=(aa-a))) flag=false;
				a=aa;
				b=bb;
			}
			if(flag) sb.append("YES");
			else sb.append("NO");
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
}