import java.util.*;
import java.io.*;

public class boj1158 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		StringBuilder sb=new StringBuilder();
		sb.append("<");
		Queue<Integer> q=new LinkedList<Integer>();
		for(int i=1;i<=n;i++) q.add(i);
		while(!q.isEmpty()) {
			int iter=k%q.size()==0?k:k%q.size();
			for(int i=0;i<iter-1;i++) q.add(q.poll());
			sb.append(q.poll()+", ");
		}
		sb.setLength(sb.length()-2);
		sb.append(">");
		System.out.println(sb);
	}
}
