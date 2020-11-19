import java.util.*;
import java.io.*;

public class boj1919 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int answer=0;
		char[] s1=br.readLine().toCharArray();
		char[] s2=br.readLine().toCharArray();
		int[] a1=new int[26];
		int[] a2=new int[26];
		for(int i=0;i<s1.length;i++) a1[s1[i]-'a']++;
		for(int i=0;i<s2.length;i++) a2[s2[i]-'a']++;
		for(int i=0;i<26;i++) answer+=Math.abs(a1[i]-a2[i]);
		System.out.println(answer);
	}
}
