import java.util.*;
import java.io.*;

public class boj1701 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s=br.readLine();
		int n=s.length();
		int answer=0;
		for(int iter=0;iter<s.length();iter++) {
			char[] pattern=s.substring(iter,n).toCharArray();
			int[] pi = new int[pattern.length];
			for (int i = 1, j = 0; i < pattern.length; i++) {
				while (j > 0 && pattern[i] != pattern[j]) j = pi[j - 1];
				if (pattern[i] == pattern[j]) pi[i] = ++j;
				answer=Math.max(pi[i],answer);
			}
		}
		System.out.println(answer);
	}
}