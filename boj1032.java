import java.io.*;
import java.util.*;

public class boj1032 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		char[] s=br.readLine().toCharArray();
		char[] answer=new char[s.length];
		for(int i=0;i<s.length;i++) answer[i]=s[i];
		for(int i=1;i<n;i++) {
			s=br.readLine().toCharArray();
			for(int j=0;j<s.length;j++) {
				if(answer[j]=='?') continue;
				if(answer[j]!=s[j]) answer[j]='?';
			}
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<s.length;i++) sb.append(answer[i]);
		System.out.println(sb.toString());
	}
}
