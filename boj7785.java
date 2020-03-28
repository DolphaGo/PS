import java.io.*;
import java.util.*;

public class boj7785 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		TreeMap<String, Boolean> map = new TreeMap<>(new Comparator<String>() {
			public int compare(String o1,String o2) {
				return o2.compareTo(o1);
			}
		});
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String name=st.nextToken();
			String state=st.nextToken();
			if(state.equals("enter")) map.put(name, true);
			else map.remove(name);
		}
		StringBuilder sb=new StringBuilder();
		for(String s:map.keySet()) sb.append(s+"\n");
		System.out.println(sb);
	}
}