import java.io.*;
import java.util.*;
public class boj8958 {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			char[] data=br.readLine().toCharArray();
			int o=0;
			int score=0;
			for(int i=0;i<data.length;i++) {
				if(data[i]=='O') score+=++o;
				else o=0;
			}
			sb.append(score+"\n");
		}
		System.out.println(sb.toString());
	}
}
