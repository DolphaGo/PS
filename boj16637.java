import java.util.*;
import java.io.*;

public class boj16637 {
	static int n,answer;
	static char[] data;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		data=br.readLine().toCharArray();
		visit=new boolean[n];
		answer=Integer.MIN_VALUE;
		go(1);
		System.out.println(answer);
	}
	static void go(int v) {
		if(v==n) {
			ArrayList<Integer> list=new ArrayList<Integer>();
			for(int i=0;i<n;i+=2) {
				int cur=data[i]-'0';
				if(visit[i]) {
					char c=data[i+1];
					int next=data[i+2]-'0';
					if(c=='+') list.add(cur+next);
					else if(c=='-') list.add(cur-next);
					else list.add(cur*next);
					i+=2;
				}else list.add(cur);
			}
			int idx=0;
			int val=list.get(0);
			for(int i=1;i<n;i+=2) {
				if(!visit[i]) {
					char c=data[i];
					if(c=='+') val+=list.get(++idx);
					else if(c=='-') val-=list.get(++idx);
					else val*=list.get(++idx);
				}
			}
			answer=Math.max(answer, val);
			return;
		}
		
		if(!visit[v-1]&&!visit[v+1]) {
			for(int i=-1;i<=1;i++) visit[v+i]=true;
			go(v+2);
			for(int i=-1;i<=1;i++) visit[v+i]=false;
		}
		go(v+2);
	}
}