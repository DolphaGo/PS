import java.io.*;
import java.util.*;

public class boj13458 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		int room[]=new int[n];
		for(int i=0;i<n;i++) {
			room[i]=Integer.parseInt(st.nextToken());
		}
		long answer=n;
		st=new StringTokenizer(br.readLine());
		int B=Integer.parseInt(st.nextToken());
		int C=Integer.parseInt(st.nextToken());
		for(int i=0;i<n;i++) {
			int val=room[i]-B;
			if(val>0) {
				answer+=(val/C);
				if(val%C!=0) answer+=1;
			}
		}
		System.out.println(answer);
	}
}