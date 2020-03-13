import java.util.*;
import java.io.*;

public class boj5618 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int data[]=new int[n];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) data[i]=Integer.parseInt(st.nextToken());
		Arrays.sort(data);
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<=data[0];i++) {
			boolean flag=true;
			for(int j=0;j<n;j++) {
				if(data[j]%i!=0) {
					flag=false;
					break;
				}
			}
			if(flag) sb.append(i+"\n");
		}
		System.out.println(sb);
	}
}
