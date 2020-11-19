import java.util.*;
import java.io.*;

public class boj16900 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		char[] s=st.nextToken().toCharArray();
		int n=s.length;
		int k=Integer.parseInt(st.nextToken());
		int[] pi=new int[n];
		for(int i=1,j=0;i<n;i++) {
			while(j>0&&s[i]!=s[j]) j=pi[j-1];
			if(s[i]==s[j]) pi[i]=++j;
		}
		long ans=n+(n-pi[n-1])*(long)(k-1);
		System.out.println(ans);
	}
}