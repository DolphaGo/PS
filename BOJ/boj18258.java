import java.io.*;
import java.util.*;

public class boj18258 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n=Integer.parseInt(br.readLine());
		Deque<Integer> dq=new LinkedList<Integer>();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			String com=st.nextToken();
			switch(com) {
			case "push":
				int X=Integer.parseInt(st.nextToken());
				dq.add(X);
				break;
			case "pop":
				if(dq.size()>0) sb.append(dq.pollFirst()+"\n");
				else sb.append(-1+"\n");
				break;
			case "size":
				sb.append(dq.size()+"\n");
				break;
			case "empty":
				if(dq.size()==0) sb.append(1+"\n");
				else sb.append(0+"\n");
				break;
			case "front":
				if(dq.size()>0) sb.append(dq.peekFirst()+"\n");
				else sb.append(-1+"\n");
				break;
			case "back":
				if(dq.size()>0) sb.append(dq.peekLast()+"\n");
				else sb.append(-1+"\n");
				break;
			}
		}
		System.out.println(sb);
	}
}
