import java.io.*;
import java.util.*;
public class boj10872 {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		long answer=1;
		while(n>0) answer*=n--;
		System.out.println(answer);
	}
}
