import java.util.*;
import java.io.*;

public class boj2947 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int data[]=new int[5];
		for(int i=0;i<5;i++) data[i]=Integer.parseInt(st.nextToken());
		StringBuilder sb=new StringBuilder();
		while(true) {
			boolean flag=false;
			for(int i=1;i<5;i++) {
				if(data[i-1]>data[i]) {
					flag=true;
					int temp=data[i-1];
					data[i-1]=data[i];
					data[i]=temp;
					for(int j=0;j<5;j++) sb.append(data[j]+" ");
					sb.append("\n");
				}
			}
			if(!flag) break;
		}
		System.out.print(sb);
	}
}