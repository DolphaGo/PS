import java.io.*;
import java.util.*;

public class boj10988 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] text=br.readLine().toCharArray();
		int len=text.length;
		boolean flag=true;
		for(int i=0;i<len/2;i++) {
			if(text[i]!=text[len-1-i]) {
				flag=false;
				break;
			}
		}
		System.out.println(flag?1:0);
	}
}
