import java.io.*;
import java.util.*;

public class boj3020 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int bottom[]=new int[h+1];
		int top[]=new int[h+1];
		for(int i=0;i<n/2;i++) {
			++bottom[Integer.parseInt(br.readLine())];
			++top[Integer.parseInt(br.readLine())];
		}
		for(int i=h-2;i>=1;i--) {
			bottom[i]+=bottom[i+1];
			top[i]+=top[i+1];
		}
		
		int min=Integer.MAX_VALUE;
		int cnt=0;
		for(int i=1;i<=h;i++) { //높이
			int val=bottom[i]+top[h+1-i];
			if(val<min) {
				min=val;
				cnt=1;
			}else if(val==min) ++cnt;
		}
		System.out.println(min+" "+cnt);
	}
}
