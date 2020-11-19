import java.io.*;
import java.util.*;

public class boj1934 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			//A=X*a B=X*b 라는 것일 텐데 (a와 b는 서로소)
			//최소 공배수는 X*a*b 를 구하는 것.
			//따라서 우선 최대 공약수를 구하자.
			//그러면 A*B/최대공약수 = 최소 공배수를 도출할 수 있다.
			int a=A>B?A:B;
			int b=A>B?B:A;
			while(b>0) {
				a-=b;
				if(a<b) {
					int temp=a;
					a=b;
					b=temp;
				}
			}//결과 : a=최대공약수
			System.out.println((A*B)/a);
		}
	}
}
