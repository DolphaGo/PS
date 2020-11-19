import java.util.*;
import java.io.*;
public class boj2217{
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int[] arr=new int[n];
		for(int i=0;i<n;i++) arr[i]=Integer.parseInt(br.readLine());
		
		//크기순 정렬
		Arrays.sort(arr);
		int answer=0;
		for(int i=n-1;i>=0;i--) {
			int w=arr[i]*(n-i);
			answer=answer<w?w:answer;
		}
		
		System.out.println(answer);
	}
}