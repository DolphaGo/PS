import java.io.*;
import java.util.*;

public class boj11365 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		while(true) {
			String s=br.readLine();
			if(s.equals("END")) break;
			
			for(int i=s.length()-1;i>=0;i--) sb.append(s.charAt(i));
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}