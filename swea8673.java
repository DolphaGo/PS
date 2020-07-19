import java.io.*;
import java.util.*;

class swea8673 {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			int answer=0;
			int K=Integer.parseInt(br.readLine());
			st=new StringTokenizer(br.readLine());
			Queue<Integer> q=new LinkedList<Integer>();
			while(st.hasMoreTokens()) q.add(Integer.parseInt(st.nextToken()));
			while(q.size()>=2) {
				int p1=q.poll();
				int p2=q.poll();
				answer+=Math.abs(p1-p2);
				if(p1<p2) q.add(p2);
				else q.add(p1);
			}
			sb.append("#"+tc+" "+answer+"\n");
		}
		System.out.print(sb);
	}
	static int pow(int a,int b) {
		return b==0?1:a*pow(a,b-1);
	}
}