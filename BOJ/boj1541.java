import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s=br.readLine();
		StringTokenizer st=new StringTokenizer(s,"+|-");
		Queue<Integer> values=new LinkedList<>();
		while(st.hasMoreTokens()) values.add(Integer.parseInt(st.nextToken()));

		int answer=values.poll();
		boolean flag=false;
		int sub=0;
		for(int i=0;i<s.length();i++){
			char c=s.charAt(i);
			if('0'<=c&&c<='9') continue;

			if(c=='+'){
				if(flag) sub+=values.poll();
				else answer+=values.poll();
			}else{
				if(!flag) {
					flag=true;
					sub+= values.poll();
				}
				else{
					answer-=sub;
					sub=values.poll();
				}
			}
		}
		answer-=sub;
		System.out.println(answer);
	}
}
