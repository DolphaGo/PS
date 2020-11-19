import java.util.*;
import java.io.*;

public class boj7567 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] dish=br.readLine().toCharArray();
		int answer=10;
		char p=dish[0];
		for(int i=1;i<dish.length;i++) {
			if(p==dish[i]) answer+=5;
			else answer+=10;
			p=dish[i];
		}
		System.out.println(answer);
	}
}
