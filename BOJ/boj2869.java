import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int a=Integer.parseInt(st.nextToken());
		int b=Integer.parseInt(st.nextToken());
		int v=Integer.parseInt(st.nextToken());
		//2 1, 3 2, 4 3, 5(성공) => 정답 4일 째
		int day=(v-a)/(a-b);
		int cur=day*(a-b);
		int answer=0;
		while(true) {
			day++;
			cur+=a;
			if(cur>=v) {
				answer=day;
				break;
			}
			cur-=b;
		}
		System.out.println(answer);
	}
}
