import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n=Integer.parseInt(br.readLine());
		PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				if(o1[1]==o2[1]) return Integer.compare(o1[0],o2[0]);
				return Integer.compare(o1[1], o2[1]);
			}
		});
		String names[]=new String[n];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			int age=Integer.parseInt(st.nextToken());
			names[i]=st.nextToken();
			pq.add(new int[] {i,age});
		}
		StringBuilder sb=new StringBuilder();
		while(!pq.isEmpty()) {
			int[] p=pq.poll();
			int idx=p[0];
			sb.append(p[1]+" "+names[idx]+"\n");
		}
		System.out.println(sb.toString());
		
	}
}