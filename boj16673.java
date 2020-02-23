import java.io.*;
import java.util.*;

public class boj16673 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int c=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		int p=Integer.parseInt(st.nextToken());
		int val=0;
		for(int i=1;i<=c;i++) val+=(k*i+p*i*i);
		System.out.println(val);
	}
}
