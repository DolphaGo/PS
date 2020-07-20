import java.util.*;
import java.io.*;
public class boj1931{
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n=Integer.parseInt(br.readLine());
		PriorityQueue<int[]> q=new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				if(o1[1]==o2[1]) return Integer.compare(o1[0],o2[0]);
				return Integer.compare(o1[1], o2[1]);
			}
		});
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			q.add(new int[] {from,to});
		}
		int answer=0;
		int t=0;
		while(!q.isEmpty()) {
			int[] p=q.poll();
			int from=p[0];
			int to=p[1];
			if(t<=from) {
				t=to;
				++answer;
			}
		}
		System.out.println(answer);
	}
}