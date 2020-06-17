import java.util.*;
import java.io.*;

public class boj1446 {
	static int answer,d;
	static ArrayList<int[]> list=new ArrayList<int[]>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			if(b>d) continue; //역주행 불가
			if(b-a<=c) continue; //지름길로서의 의미가 X
			list.add(new int[] {a,b,c});
		}
		
		Collections.sort(list,new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				if(o1[0]==o2[0]) return Integer.compare(o1[1],o2[1]);
				return Integer.compare(o1[0],o2[0]);
			}
		});
		
		answer=Integer.MAX_VALUE;
		go(0,0);
		System.out.println(answer);
	}
	static void go(int v,int cost) {
		if(cost>=answer) return;
		if(v==d) {
			answer=cost;
			return;
		}
		//지름길 이용
		for(int i=0;i<list.size();i++) {
			int[] p=list.get(i);
			if(p[0]==v) go(p[1],cost+p[2]);
		}
		//그냥 이동
		go(v+1,cost+1);
	}
}
