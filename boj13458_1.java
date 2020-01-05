import java.util.*;
import java.io.*;

public class boj13458_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n=Integer.parseInt(br.readLine());
		st=new StringTokenizer(br.readLine());
		int[] student=new int[n];
		for(int i=0;i<n;i++) {
			student[i]=Integer.parseInt(st.nextToken());
		}
		st=new StringTokenizer(br.readLine());
		int B=Integer.parseInt(st.nextToken());
		int C=Integer.parseInt(st.nextToken());
		long answer=n;
		for(int i=0;i<n;i++) {
			long val=(student[i]-B);
			if(val<=0) continue;
			if(val%C!=0) answer+=(val/C+1);
			else answer+=val/C;
		}
		System.out.println(answer);
	}
}
