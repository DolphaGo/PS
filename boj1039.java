import java.io.*;
import java.util.*;

public class boj1039 {
	static int k,answer,len;
	static boolean visit[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		visit=new boolean[1000001][11];
		int n=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		len=(n+"").length();
		visit[n][0]=true;
		answer=Integer.MIN_VALUE;
		go(n,0);
		System.out.println(answer==Integer.MIN_VALUE?-1:answer);
	}
	static StringBuilder sb=new StringBuilder();
	static void go(int cur,int depth) {
		if(depth==k) {
			answer=answer<cur?cur:answer;
			return;
		}
		sb.setLength(0);
		sb.append(cur+"");
		for(int i=0;i<len-1;i++) {
			for(int j=i+1;j<len;j++) {
				if(i==0&&sb.charAt(j)=='0') continue;
				char temp=sb.charAt(i);
				sb.setCharAt(i, sb.charAt(j));
				sb.setCharAt(j, temp);
				int val=Integer.parseInt(sb.toString());
				if(!visit[val][depth+1]) {
					visit[val][depth+1]=true;
					go(val,depth+1);
				}
				sb.setCharAt(j, sb.charAt(i));
				sb.setCharAt(i, temp);
			}
		}
	}
}