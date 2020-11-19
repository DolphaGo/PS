import java.io.*;
import java.util.*;

public class boj1259 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s=br.readLine();
		StringBuilder sb=new StringBuilder();
		while(!s.equals("0")) {
			boolean flag=true;
			int i=0;
			int j=s.length()-1;
			while(i<j) {
				if(s.charAt(i)==s.charAt(j)) {
					i++;
					j--;
				}else {
					flag=false;
					break;
				}
			}
			sb.append(flag?"yes"+"\n":"no"+"\n");
			s=br.readLine();
		}
		System.out.println(sb);
	}
}