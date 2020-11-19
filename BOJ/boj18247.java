import java.io.*;
import java.util.*;

public class boj18247 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			if(n>=12&&m>=4) sb.append(11*m+4+"\n");
			else sb.append(-1+"\n");
		}
		System.out.println(sb);
	}
}