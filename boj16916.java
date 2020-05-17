import java.util.*;
import java.io.*;

public class boj16916 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] par=br.readLine().toCharArray();
		char[] pat=br.readLine().toCharArray();
		int[] pi=new int[pat.length];
		for(int i=1,j=0;i<pat.length;i++) {
			while(j>0&&pat[i]!=pat[j]) j=pi[j-1];
			if(pat[i]==pat[j]) pi[i]=++j;
		}
		boolean flag=false;
		for(int i=0,j=0;i<par.length;i++) {
			while(j>0&&par[i]!=pat[j]) j=pi[j-1];
			if(par[i]==pat[j]) {
				if(j==pat.length-1) {
					flag=true;
					break;
				}else ++j;
			}
		}
		System.out.println(flag?1:0);
	}
}