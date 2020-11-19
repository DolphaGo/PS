package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int res[]=new int[42];
		for(int i=0;i<10;i++) {
			int val=Integer.parseInt(br.readLine());
			res[val%42]++;
		}
		int answer=0;
		for(int i=0;i<42;i++) if(res[i]!=0) answer++;
		System.out.println(answer);
	
	}
}
