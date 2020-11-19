import java.util.*;
import java.io.*;

public class boj1195 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] a=br.readLine().toCharArray();
		char[] b=br.readLine().toCharArray();
		boolean flag=false;
		if(a.length<b.length) flag=true;
		if(flag) {
			char[] temp=a.clone();
			a=b.clone();
			b=temp.clone();
		}
		int alen=a.length;
		int blen=b.length;
		
		char[] c=new char[alen+2*blen];
		for(int i=blen;i<blen+alen;i++) {
			c[i]=a[i-blen];
		}
		int answer=Integer.MAX_VALUE;

		for(int i=0;i<alen+blen;i++) {
		//시작 지점
			int len=0;
			flag=true;
			for(int j=i;j<i+blen;j++) {
				int aval=c[j]-'0';
				int bval=b[j-i]-'0';
				if(aval+bval>3) {
					flag=false;
					break;
				}
			}
			if(flag) {
				if(i<alen) {
					if(i<blen) len=alen+blen-i;
					else len=alen;
				}else len=i;
			answer=answer>len?len:answer;
			}
		}
		
		System.out.println(answer);
	}
}
