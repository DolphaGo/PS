import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int[] arr;
	static boolean[] visit;
	static Long answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		arr=new int[n];
		visit=new boolean[n];
		answer=0L;
		go(0);
		System.out.println(answer);
	}
	static void go(int v) {
		if(v==n) {
			++answer;
			return;
		}
		for(int i=0;i<n;i++) {
			if(visit[i]) continue;
			if(isPromising(v,i)) {
				visit[i]=true;
				arr[v]=i;
				go(v+1);
				visit[i]=false;
			}
		}
	}
	static boolean isPromising(int a,int b) {
		for(int y=0;y<a;y++) {
			int val=arr[y];
			if(a-y==Math.abs(val-b)) return false;
		}
		return true;
	}
}
