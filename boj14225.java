import java.util.*;
import java.io.*;
public class Main {
	static HashMap<Integer,Boolean> map=new HashMap<>();
	static int n;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());
		arr=new int[n];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		go(0,0);
		for(int i=1;;i++) {
			if(map.get(i)==null) {
				System.out.println(i);
				return;
			}
		}
	}
	static void go(int v,int sum) {
		if(map.get(sum)==null) map.put(sum,true);
		if(v==n) return;
		go(v+1,sum+arr[v]);
		go(v+1,sum);
	}
}