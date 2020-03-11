import java.util.*;
import java.io.*;

public class boj12015 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		ArrayList<Integer>list=new ArrayList<Integer>();
		list.add(Integer.MAX_VALUE);
		int mid=0;
		for(int i=0;i<n;i++) {
			int val=Integer.parseInt(st.nextToken());
			int lastVal=list.get(list.size()-1);
			if(lastVal<val) list.add(val);
			else {
				int start=0;
				int end=list.size();
				while(start<end) {
					mid=(start+end)/2;
					if(list.get(mid)<val) start=mid+1;
					else end=mid;
				}
				if(list.get(end)>val) list.set(end, val);
			}
		}
		System.out.println(list.toString());
		System.out.println(list.size());
	}
}
