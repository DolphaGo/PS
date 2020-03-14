import java.util.*;
import java.io.*;

public class boj11170 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int cnt=0;
			for(int i=n;i<=m;i++) {
				String s=i+"";
				for(int j=0;j<s.length();j++) {
					if(s.charAt(j)=='0') ++cnt;
				}
			}
			System.out.println(cnt);
		}
	}
}
