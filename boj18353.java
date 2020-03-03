import java.io.*;
import java.util.*;

public class boj18353 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] data=new int[n];
		for(int i=0;i<n;i++) data[i]=Integer.parseInt(st.nextToken());
		ArrayList<Integer> list=new ArrayList<>();
		list.add(data[0]);
		
		//O(N^2)으로도 가능. 이분탐색 이용시 O(NlogN)
		for(int i=1;i<n;i++) {
			int cur=data[i];
			boolean flag=false;
			for(int j=0;j<list.size();j++) {
				if(list.get(j)<cur) {
					list.set(j, cur);
					flag=true;
					break;
				}
			}
			if(!flag&&list.get(list.size()-1)>cur) list.add(cur);
		}
		System.out.println(n-list.size());
	}
}
