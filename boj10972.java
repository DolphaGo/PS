import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		int data[]=new int[n];
		for(int i=0;i<n;i++) data[i]=Integer.parseInt(st.nextToken()); //현재 데이터에서 순서
		//next_permutation 구현
		int i=n-1;
		StringBuilder sb=new StringBuilder();
		while(i-1>=0&&data[i-1]>data[i]) --i;
		if(i==0) System.out.println(-1);
		else {
			for(int j=n-1;j>=i;j--) {
				if(data[j]>data[i-1]) {
					int temp=data[j];
					data[j]=data[i-1];
					data[i-1]=temp;
					for(int k=0;k<i;k++) sb.append(data[k]+" ");
					for(int k=n-1;k>=i;k--) sb.append(data[k]+" ");
					break;
				}
			}
			System.out.println(sb.toString());
		}
	}
}