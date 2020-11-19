import java.util.*;
import java.io.*;

public class Main {
	static int n,m;
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		int[] arr=new int[n];
		st=new StringTokenizer(br.readLine());
		
		for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		select=new int[m];
		go(arr,0);
		System.out.print(sb);
	}
	static int[] select;
	static void go(int[] arr,int v) {
		if(v==m) {
			for(int i=0;i<m;i++) {
				sb.append(select[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0;i<n;i++) {
			select[v]=arr[i];
			go(arr,v+1);
		}
	}
}
