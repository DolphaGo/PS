import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int cnt=0;
		for(int i=1;i<=5;i++) {
			boolean flag=false;
			String s=br.readLine();
			for(int j=0;j<=s.length()-3;j++) {
				String ts=s.substring(j,j+3);
				if(ts.equals("FBI")) flag=true;
			}
			if(flag) {
				++cnt;
				System.out.print(i+" ");
			}
		}
		if(cnt==0) System.out.println("HE GOT AWAY!");
	}
}
