import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int tree[]=new int[n];
		st=new StringTokenizer(br.readLine());
		int s=0;
		int e=0;
		for(int i=0;i<n;i++) {
			tree[i]=Integer.parseInt(st.nextToken());
			e=tree[i]>e?tree[i]:e;
		}
		while(s+1!=e) {
			int mid=(s+e)/2;
			long res=0;
			for(int i=0;i<n;i++) if(tree[i]>mid) res+=tree[i]-mid;
			if(res>=m) s=mid;//s의 의미: 목표 벌목량을 얻는 경우에서 가장 큰 높이 
			else e=mid; //e의 의미: 목표 벌목량을 못얻는 경우에서 가장 작은 높이
		}
		System.out.println(s);
	}
}
