import java.io.*;
import java.util.*;

public class boj2845 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		int L=Integer.parseInt(st.nextToken());
		int P=Integer.parseInt(st.nextToken());
		L*=P;
		
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<5;i++) {
			int val=Integer.parseInt(st.nextToken());
			sb.append((val-L)+" ");
		}
		System.out.print(sb);
	}
}