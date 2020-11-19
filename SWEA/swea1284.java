import java.io.*;
import java.util.*;

class swea1284 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();

		int T=Integer.parseInt(br.readLine());
		int P,Q,R,S,W;
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			//1리터당 P
			P=Integer.parseInt(st.nextToken());
			//R리터보다 많으면 1리터당 S, R리터보다 작으면 Q
			Q=Integer.parseInt(st.nextToken());
			R=Integer.parseInt(st.nextToken()); 
			S=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			
			P*=W;
			if(W>R) Q+=(W-R)*S;
			int answer=Math.min(P,Q);
			sb.append("#"+tc+" "+answer+"\n");
		}
		System.out.print(sb);
	}
}