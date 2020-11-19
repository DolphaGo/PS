import java.io.*;
import java.util.*;

public class boj17362 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int answer=0;
		if(n%8==1||n%8==5) System.out.println(n%8);
		else {
			if(n%8==2||n%8==0) answer=2;
			else if(n%8==3||n%8==7) answer=3;
			else answer=4;
			System.out.println(answer);
		}
	}
}