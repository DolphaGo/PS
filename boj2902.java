import java.io.*;
import java.util.*;

public class boj2902 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		char[] name=br.readLine().toCharArray();
		for(int i=0;i<name.length;i++) {
			if('A'<=name[i]&&name[i]<='Z') sb.append(name[i]);
		}
		System.out.println(sb);
	}
}