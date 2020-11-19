import java.io.*;
import java.util.*;

public class boj16637_0602 {
	static int n,answer;
	static char[] arr;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		arr=br.readLine().toCharArray();
		visit=new boolean[n];
		answer=Integer.MIN_VALUE;
		go(1);
		System.out.println(answer);
	}
	static ArrayList<Integer> num=new ArrayList<Integer>();
	static void go(int v) {
		if(v==n) {
			//계산 시작
			num.clear();
			for(int i=0;i<n;i+=2) {
				if('0'<=arr[i]&&arr[i]<='9') {
					int val=arr[i]-'0';
					if(visit[i]) {
						int nval=arr[i+2]-'0';
						switch(arr[i+1]) {
						case '+':
							val+=nval;
							break;
						case '-':
							val-=nval;
							break;
						case '*':
							val*=nval;
							break;
						}
						num.add(val);
						i+=2;
					}else num.add(val);
				}
			}
			int idx=0;
			int res=num.get(idx++);
			for(int i=1;i<n;i+=2) {
				if(!visit[i]) {
					int next=num.get(idx++);
					switch(arr[i]) {
					case '+':
						res+=next;
						break;
					case '-':
						res-=next;
						break;
					case '*':
						res*=next;
						break;
					}
				}
			}
			answer=answer<res?res:answer;
			return;
		}
		
		if(!visit[v-1]&&!visit[v+1]) {
			visit[v-1]=true;
			visit[v]=true;
			visit[v+1]=true;
			go(v+2);
			visit[v-1]=false;
			visit[v]=false;
			visit[v+1]=false;
		}
		go(v+2);
	}
	
}