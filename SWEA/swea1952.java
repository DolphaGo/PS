import java.io.*;
import java.util.*;

class swea1952 {
	static int answer;
	static int[] cost,arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			cost=new int[4];
			arr=new int[12];
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<4;i++) cost[i]=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<12;i++) arr[i]=Integer.parseInt(st.nextToken());
			
			answer=Integer.MAX_VALUE;
			go(0,0);
			sb.append("#"+tc+" "+answer+"\n");
		}
		System.out.print(sb);
	}
	static void go(int v,int c) {
		if(c>=answer) return;
		if(v>=12) {
			answer=c;
			return;
		}
		for(int i=0;i<4;i++) {
			switch(i) {
			case 0:
				int cur=arr[v];
				go(v+1,c+cur*cost[0]);
				break;
			case 1:
				go(v+1,c+cost[1]);
				break;
			case 2:
				go(v+3,c+cost[2]);
				break;
			case 3:
				go(v+12,c+cost[3]);
				break;
			}
		}
	}
}