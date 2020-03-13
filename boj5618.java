import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		int g=Integer.parseInt(st.nextToken());
		while(st.hasMoreTokens()) {
			int num=Integer.parseInt(st.nextToken());
			g=gcd(g,num);
		}
		PriorityQueue<Integer> q=new PriorityQueue<>();
		StringBuilder sb=new StringBuilder();
		for(int i=1;i*i<=g;i++) {
			if(g%i==0) {
				q.add(i);
				if(i*i!=g) q.add(g/i);
			}
		}
        while(!q.isEmpty()) sb.append(q.poll()+"\n");
		System.out.println(sb);
	}
    static int gcd(int a,int b){
        return b==0?a:gcd(b,a%b);
    }
}
