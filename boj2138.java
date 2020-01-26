import java.io.*;
import java.util.*;

public class boj2138 {
	static char src[][],dst[];
	static int n,answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		String s=br.readLine();
		src=new char[2][n];
		src[0]=s.toCharArray();
		src[1]=s.toCharArray();
		dst=br.readLine().toCharArray();
		answer=Integer.MAX_VALUE;
		//0번째 스위치를 누르지 않고 시작
		go(1,0,0);
		//0번째 스위치를 누르고 시작
		push(0,1);
		go(1,1,1);
		System.out.println(answer==Integer.MAX_VALUE?-1:answer);
	}
	static void push(int idx,int type) {
		for(int i=idx-1;i<=idx+1;i++) {
			if(i>=0&&i<n) src[type][i]=src[type][i]=='1'?'0':'1';
		}
	}
	static void go(int idx,int cnt,int type) {
		if(idx==n) {
			if(src[type][idx-1]==dst[idx-1]) answer=answer>cnt?cnt:answer;
			return;
		}
		if(src[type][idx-1]!=dst[idx-1]) {
			push(idx,type);
			go(idx+1,cnt+1,type);
		}else go(idx+1,cnt,type);
	}
}