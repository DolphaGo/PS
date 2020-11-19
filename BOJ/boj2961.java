import java.util.*;
import java.io.*;

public class boj2961 {
	static int n,answer;
	static int data[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());
		data=new int[n][2];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			data[i][0]=Integer.parseInt(st.nextToken());
			data[i][1]=Integer.parseInt(st.nextToken());
		}
		answer=Integer.MAX_VALUE;
		go(0,0,1,0);//신맛, 쓴맛
		System.out.println(answer);
	}
	static void go(int v,int select,int sour,int bitter) {
		if(select>=1) {
			int diff=Math.abs(sour-bitter);
			answer=answer>diff?diff:answer;
		}
		if(v==n) return;
		go(v+1,select+1,sour*data[v][0],bitter+data[v][1]);
		go(v+1,select,sour,bitter);
	}
}
