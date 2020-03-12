import java.util.*;
import java.io.*;

public class boj5586 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		char[] data=br.readLine().toCharArray();
		int J=0;
		int I=0;
		for(int i=0;i<=data.length-3;i++) {
			if(data[i]!='J'&&data[i]!='I') continue;
			if(data[i+1]=='O'&&data[i+2]=='I') {
				if(data[i]=='J') ++J;
				else ++I;
			}
		}
		System.out.println(J+"\n"+I);
	}
}
