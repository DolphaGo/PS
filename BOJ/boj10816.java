import java.util.*;
import java.io.*;

public class boj10816 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n=Integer.parseInt(br.readLine());
		st=new StringTokenizer(br.readLine());
		HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
		for(int i=0;i<n;i++) {
			int val=Integer.parseInt(st.nextToken());
			if(map.get(val)==null) {
				map.put(val,1);
			}else map.replace(val, map.get(val)+1);
		}
		
		int m=Integer.parseInt(br.readLine());
		st=new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<m;i++) {
			int val=Integer.parseInt(st.nextToken());
			int res=map.get(val)==null?0:map.get(val);
			sb.append(res+" ");
		}
		System.out.println(sb);
	}
}