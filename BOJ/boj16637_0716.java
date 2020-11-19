import java.util.*;
import java.io.*;

public class boj16637_0716 {
	static int n,answer;
	static char[] arr;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		n=Integer.parseInt(br.readLine());
		arr=br.readLine().toCharArray();
		visit=new boolean[n];
		
		answer=Integer.MIN_VALUE;
		go(1);
		System.out.println(answer);
	}
	static void go(int v) {
		if(v>=n) {
			Queue<Integer> q=new LinkedList<>();
			for(int i=0;i<n;i+=2) {
				int prev=arr[i]-'0';
				if(visit[i]) {
					char c=arr[i+1];
					int next=arr[i+2]-'0';
					if(c=='+') prev+=next;
					else if(c=='-') prev-=next;
					else prev*=next;
					q.add(prev);
					i+=2;
				}else q.add(arr[i]-'0');
			}
			
			int val=q.poll();
			for(int i=1;i<n;i+=2) {
				if(!visit[i]) {
					if(arr[i]=='+') val+=q.poll();
					else if(arr[i]=='-') val-=q.poll();
					else val*=q.poll();
				}
			}
			answer=answer<val?val:answer;
			return;
		}
		
		if(!visit[v-1]&&!visit[v+1]) {
			for(int i=-1;i<=1;i++) visit[v+i]=true;
			go(v+4);
			for(int i=-1;i<=1;i++) visit[v+i]=false;
		}
		go(v+2);
	}
}
