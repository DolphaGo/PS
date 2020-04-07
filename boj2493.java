import java.util.*;
import java.io.*;

public class boj2493 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		int data[]=new int[N];
		for(int i=0;i<N;i++) data[i]=Integer.parseInt(st.nextToken());
		
		PriorityQueue<int[]> q=new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		int answer[]=new int[N];
		q.add(new int[] {data[N-1],N-1});
		for(int i=N-2;i>=0;i--) {
			while(q.size()>0&&q.peek()[0]<data[i]) {
				int[] p=q.poll();
				answer[p[1]]=i+1;
			}
			q.add(new int[] {data[i],i});
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<N;i++) sb.append(answer[i]+" ");
		System.out.println(sb);
	}
}
