import java.util.*;
import java.io.*;

public class boj5656 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int tc=0;
		while(true) {
			++tc;
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			String c=st.nextToken();
			int b=Integer.parseInt(st.nextToken());
			if(c.equals("E")) break;
			boolean flag=false;;
			switch(c) {
			case ">":
				if(a>b) flag=true;
				else flag=false;
				break;
			case ">=":
				if(a>=b) flag=true;
				else flag=false;
				break;
			case "<":
				if(a<b) flag=true;
				else flag=false;
				break;
			case "<=":
				if(a<=b) flag=true;
				else flag=false;
				break;
			case "==":
				if(a==b) flag=true;
				else flag=false;
				break;
			case "!=":
				if(a!=b) flag=true;
				else flag=false;
				break;
			}
			sb.append("Case "+tc+": "+flag+"\n");
		}
		System.out.println(sb);
	}
}
