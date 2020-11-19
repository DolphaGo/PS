import java.util.*;
import java.io.*;

public class boj2960 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		boolean c[]=new boolean[n+1];
		
		int cnt=0;
		int answer=0;
		loop:for(int i=2;i<=n;i++) {
			if(c[i]) continue;
			++cnt;
			if(cnt==k) {
				answer=i;
				break;
			}
			for(int j=i*2;j<=n;j+=i) {
				if(c[j]) continue;
				c[j]=true;
				++cnt;
				if(cnt==k) {
					answer=j;
					break loop;
				}
			}
		}
		System.out.println(answer);
	}
}