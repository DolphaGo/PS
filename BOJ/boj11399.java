import java.util.*;
import java.io.*;
public class boj11399{
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n=Integer.parseInt(br.readLine());
		st=new StringTokenizer(br.readLine());
		int[] arr=new int[n];
		for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		int answer=arr[0];
		for(int i=1;i<n;i++) {
			arr[i]+=arr[i-1];
			answer+=arr[i];
		}
		System.out.println(answer);
	}
}