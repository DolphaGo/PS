import java.util.*;
import java.io.*;

public class boj9613 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int t=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=t;tc++) {
			st=new StringTokenizer(br.readLine());
			int num=Integer.parseInt(st.nextToken());
			int data[]=new int[num];
			for(int i=0;i<num;i++) {
				data[i]=Integer.parseInt(st.nextToken());
			}
			long answer=0;
			for(int i=0;i<num-1;i++) {
				for(int j=i+1;j<num;j++) {
					answer+=gcd(data[i],data[j]);
				}
			}
			sb.append(answer+"\n");
		}
		System.out.println(sb);
	}
	static int gcd(int a,int b) {
		return b==0?a:gcd(b,a%b);
	}
}
