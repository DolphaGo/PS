import java.util.*;
import java.io.*;
public class swea5658 {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			long answer=0;
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());
			TreeSet<String> ts=new TreeSet<String>();
			char[] arr=br.readLine().toCharArray();
			ArrayList<Character> list=new ArrayList<Character>();
			for(int i=0;i<n;i++) list.add(arr[i]);
			int len=n/4;
			TreeSet<Long> set=new TreeSet<Long>(new Comparator<Long>() {
				public int compare(Long o1,Long o2) {
					return Long.compare(o2, o1);
				}
			});
			StringBuilder sb=new StringBuilder();
			for(int iter=0;iter<len;iter++) {
				for(int i=0;i<=n-len;i+=len) {
					sb.setLength(0);
					for(int j=i;j<i+len;j++) {
						sb.append(list.get(j));
					}
					set.add(Long.parseLong(sb.toString(),16));
				}
				
				list.add(0, list.get(list.size()-1));
				list.remove(list.size()-1);
			}
			int cnt=0;
			for(long val:set) {
				++cnt;
				if(cnt==k) {
					answer=val;
					break;
				}
			}
			System.out.println("#"+tc+" "+answer);
		}
	}
}
