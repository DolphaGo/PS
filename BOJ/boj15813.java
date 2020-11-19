import java.util.*;
import java.io.*;

public class boj15813 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		char[] name=br.readLine().toCharArray();
		int answer=0;
		for(int i=0;i<n;i++) {
			char c=name[i];
			answer+=(c-'A')+1;
		}
		System.out.println(answer);
	}
}
