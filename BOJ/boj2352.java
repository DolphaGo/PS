import java.util.*;
import java.io.*;

public class boj2352 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 최장 증가수열을 찾는 문제.
		ArrayList<Integer> list = new ArrayList<>();
		list.add(Integer.parseInt(st.nextToken()));
		for (int i = 1; i < n; i++) {
			//lower-bound 이용
			int val=Integer.parseInt(st.nextToken());
			if(val>list.get(list.size()-1)) list.add(val);
			else {
				int s=0;
				int e=list.size()-1;
				while(s<e) {
					int mid=(s+e)>>1;
					if(list.get(mid)<val) s=mid+1;
					else e=mid;
				}
				list.set(e, val);
			}
		}
		System.out.println(list.size());
	}
}
