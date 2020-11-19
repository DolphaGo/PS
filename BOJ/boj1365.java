import java.util.*;
import java.io.*;

public class boj1365 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		
		int n=Integer.parseInt(br.readLine());
		st=new StringTokenizer(br.readLine());
		ArrayList<Integer> list=new ArrayList<Integer>();
		list.add(Integer.parseInt(st.nextToken()));
		for(int i=1;i<n;i++) {
			int val=Integer.parseInt(st.nextToken());
			if(list.get(list.size()-1)<val) list.add(val);
			else {
				int s=0;
				int e=list.size()-1;
				while(s<e) {
					int mid=(s+e)>>1;
					if(list.get(mid)<val) s=mid+1;
					else e=mid;
				}
				list.set(e,val);
			}
		}
		System.out.println(n-list.size());
	}
}
