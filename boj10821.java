import java.util.*;
import java.io.*;

public class boj10821 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] data=br.readLine().toCharArray();
		int answer=0;
		for(int i=0;i<data.length;i++) {
			if(data[i]==',') ++answer;
		}
		System.out.println(++answer);
	}
}
