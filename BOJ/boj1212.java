import java.util.*;
import java.io.*;

public class boj1212 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		if (!s.equals("0")) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < s.length(); i++) {
				char cur = s.charAt(i);
				String temp = Integer.toBinaryString(cur - '0');
				for (int j = 0; j < 3 - temp.length(); j++)	sb.append(0);
				sb.append(temp);
			}
			while (sb.charAt(0) == '0') sb.deleteCharAt(0);
			System.out.println(sb);
		} else	System.out.println(0);
	}
}
