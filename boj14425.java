import java.io.*;
import java.util.*;

public class boj14425 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		HashMap<String, Boolean> map=new HashMap<String, Boolean>();
		for(int i=0;i<n;i++) {
			String s=br.readLine();
			map.put(s,true);
		}
		int answer=0;
		for(int i=0;i<m;i++) {
			String s=br.readLine();
			if(map.get(s)==null) continue;
			++answer;
		}
		System.out.println(answer);
	}
}