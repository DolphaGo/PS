import java.io.*;
import java.util.*;

public class boj11004 {
	static int n;
	static int arr[];
	static void mergesort(int s,int e) {
		int mid=(s+e)/2;
		int A[]=new int[mid-s];
		int B[]=new int[e-mid];
		for(int i=s;i<mid;i++) A[i-s]=arr[i];
		for(int i=mid;i<e;i++) B[i-mid]=arr[i];
		int len=e-s;
		int temp[]=new int[len];
		int a=0;
		int b=0;
		int idx=0;
		while(a<A.length&&b<B.length) {
			if(A[a]<B[b]) temp[idx++]=A[a++];
			else temp[idx++]=B[b++];
		}
		
		if(a==A.length) {
			while(b!=B.length) temp[idx++]=B[b++];
		}else {
			while(a!=A.length) temp[idx++]=A[a++];
		}
		
		for(int i=s;i<e;i++) {
			arr[i]=temp[i-s];
		}
		
	}
	static void partition(int s,int e) {
		if(s+1<e) {
			int mid=(s+e)/2;
			partition(s,mid);
			partition(mid,e);
			mergesort(s,e);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		arr=new int[n];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());
		
		partition(0,n);
		System.out.println(arr[k-1]);
	}
}