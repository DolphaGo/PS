import java.util.*;
import java.io.*;

public class boj4354 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		while(true) {
			String s=br.readLine();
			if(s.equals(".")) break;
			int n=s.length();
			int[] pi=new int[n];
			for(int i=1,j=0;i<n;i++) {
				while(j>0&&s.charAt(i)!=s.charAt(j)) j=pi[j-1];
				if(s.charAt(i)==s.charAt(j)) pi[i]=++j;
			}
			int x=n-pi[n-1];
			if(n%x!=0) sb.append(1+"\n");
			else sb.append((n/x)+"\n");
		}
		System.out.print(sb);
	}
}