import java.util.*;
import java.io.*;

public class boj10819 {
	static int n,answer;
	static int[] arr,tmp;
	static boolean[] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		arr=new int[n];
		tmp=new int[n];
		visit=new boolean[n];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());
		answer=Integer.MIN_VALUE;
		go(0);
		System.out.println(answer);
	}
	static void go(int v) {
		if(v==n) {
			int ret=Test();
			answer=answer<ret?ret:answer;
			return;
		}
		for(int i=0;i<n;i++) {
			if(visit[i]) continue;
			visit[i]=true;
			tmp[v]=arr[i];
			go(v+1);
			visit[i]=false;
		}
	}
	static int Test() {
		int ret=0;
		for(int i=0;i<n-1;i++) {
			int prev=tmp[i];
			int next=tmp[i+1];
			ret+=Math.abs(prev-next);
		}
		return ret;
	}
}