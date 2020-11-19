import java.io.*;
import java.util.*;

public class boj10973 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int data[]=new int[n];
		for(int i=0;i<n;i++) data[i]=Integer.parseInt(st.nextToken());
		StringBuilder sb=new StringBuilder();
		boolean flag=false;
		loop:for(int i=n-1;i>0;i--) {
			if(data[i]<data[i-1]) {
				for(int j=n-1;j>=i;j--) {
					if(data[j]<data[i-1]) {
						int temp=data[i-1];
						data[i-1]=data[j];
						data[j]=temp;
						
						for(int k=0;k<i;k++) sb.append(data[k]+" ");
						for(int k=n-1;k>=i;k--) sb.append(data[k]+" ");
						System.out.println(sb.toString());
						flag=true;
						break loop;
					}
				}
			}
		}
		if(!flag) System.out.println(-1);
	}
}