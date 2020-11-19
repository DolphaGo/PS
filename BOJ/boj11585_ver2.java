import java.util.*;
import java.io.*;

public class boj11585_ver2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int len=Integer.parseInt(br.readLine());
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		char[] pattern=new char[len];
		for(int i=0;i<len;i++) pattern[i]=st.nextToken().charAt(0);
		
		//현재 룰렛
		st=new StringTokenizer(br.readLine());
		char[] text=new char[len*2];
		for(int i=0;i<len;i++) {
			text[i]=st.nextToken().charAt(0);
			text[i+len]=text[i];
		}
		
		int[] pi=new int[len];
		int j=0;
		for(int i=1;i<len;i++) {
			while(j>0&&pattern[i]!=pattern[j]) j=pi[j-1];
			if(pattern[i]==pattern[j]) pi[i]=++j;
		}

		j=0;
		int answer=0;
		for(int i=1;i<text.length;i++) {
			while(j>0&&text[i]!=pattern[j]) j=pi[j-1];
			if(text[i]==pattern[j]) {
				if(j==len-1) {
					++answer;
					j=pi[j];
				}else ++j;
			}
		}
		if(len%answer==0) {
			len/=answer;
			answer=1;
		}
		
		System.out.println(answer+"/"+len);
	}
}