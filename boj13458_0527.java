import java.io.*;
import java.util.*;

public class boj13458_0527 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n=Integer.parseInt(br.readLine());
		long answer=n;
		int a[]=new int[n];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			a[i]=Integer.parseInt(st.nextToken());
		}
		st=new StringTokenizer(br.readLine());
		int b=Integer.parseInt(st.nextToken());
		int c=Integer.parseInt(st.nextToken());
		for(int i=0;i<n;i++) {
			int res=a[i]-b;
			if(res<0) continue;
			if(res%c==0) answer+=res/c;
			else answer+=res/c+1;
		}
		System.out.println(answer);
	}
}