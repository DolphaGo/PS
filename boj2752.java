import java.util.*;
import java.io.*;

public class boj2752 {
	static void rsort(int[] arr,int n,int exp) {
		int[] buffer=new int[n];
		int[] count=new int[10];
		
		for(int i=0;i<n;i++) {
			int res=(arr[i]/exp)%10;
			count[res]++;
		}
		
		for(int i=1;i<=9;i++) {
			count[i]+=count[i-1];
		}
		
		for(int i=n-1;i>=0;i--) {
			int res=(arr[i]/exp)%10;
			int idx=count[res];
			buffer[idx-1]=arr[i];
			count[res]--;
		}
		
		for(int i=0;i<n;i++) {
			arr[i]=buffer[i];
		}
		
	}
	static void RadixSort(int[] arr) {
		int n=arr.length;
		int max=0;
		for(int i=0;i<n;i++) max=Math.max(arr[i],max);
		
		for(int i=1;max/i>0;i*=10) {
			rsort(arr,n,i);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int[] arr=new int[3];
		for(int i=0;i<3;i++) arr[i]=Integer.parseInt(st.nextToken());
		RadixSort(arr);
		for(int i=0;i<3;i++) System.out.print(arr[i]+" ");
	}
}
