import java.util.*;
import java.io.*;

public class boj1305 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int len=Integer.parseInt(br.readLine());
		char[] pattern=br.readLine().toCharArray();
		int[] pi=new int[len];
		int j=0;
		for(int i=1;i<len;i++) {
			while(j>0&&pattern[i]!=pattern[j]) j=pi[j-1];
			if(pattern[i]==pattern[j]) pi[i]=++j;
		}
		System.out.println(len-pi[len-1]);
	}
}