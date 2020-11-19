import java.util.*;
import java.io.*;

public class boj11585 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int len=Integer.parseInt(br.readLine());
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		char[] pattern=new char[len];
		for(int i=0;i<len;i++) pattern[i]=st.nextToken().charAt(0);
		
		//현재 룰렛
		br.readLine();
		
		int[] pi=new int[len];
		int j=0;
		for(int i=1;i<len;i++) {
			while(j>0&&pattern[i]!=pattern[j]) j=pi[j-1];
			if(pattern[i]==pattern[j]) pi[i]=++j;
		}
		System.out.println("1/"+(len-pi[len-1]));
	}
}