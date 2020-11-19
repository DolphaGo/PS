import java.util.*;
import java.io.*;

public class boj9252 {
	public static void main(String[] args) throws IOException {
    	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    	char[] s1=br.readLine().toCharArray();
    	char[] s2=br.readLine().toCharArray();
    	
    	int h=s1.length;
    	int w=s2.length;
    	
    	int[][] dp=new int[h+1][w+1];

    	for(int y=1;y<=h;y++) {
    		char c1=s1[y-1];
    		for(int x=1;x<=w;x++) {
    			char c2=s2[x-1];
    			if(c1==c2) dp[y][x]=dp[y-1][x-1]+1;
    			else dp[y][x]=Math.max(dp[y-1][x], dp[y][x-1]);
    		}
    	}
    	
    	int answer=dp[h][w];
		System.out.println(answer);
		if(answer!=0) {
			StringBuilder sb=new StringBuilder();
			Stack<Character> stack=new Stack<Character>();
			int x=w;
			int y=h;
			int cur=dp[y][x];
			while(dp[y][x]!=0) {
				if(dp[y-1][x]==cur) {
					y-=1;
				}else if(dp[y][x-1]==cur) {
					x-=1;
				}else if(dp[y-1][x-1]==cur-1) {
					stack.push(s2[x-1]);
					cur-=1;
					y-=1;
					x-=1;
				}
			}
			while(!stack.isEmpty()) {
				sb.append(stack.pop());
			}
			System.out.println(sb);
		}
	}
}
