package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int val=1;
		for(int i=0;i<3;i++) {
			val*=Integer.parseInt(br.readLine());
		}
		int digit[]=new int[10];
		char data[]=(val+"").toCharArray();
		for(int i=0;i<data.length;i++) {
			int value=data[i]-'0';
			digit[value]++;
		}
		for(int i=0;i<10;i++) {
			System.out.println(digit[i]);
		}
	}
}
