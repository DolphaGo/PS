import java.io.*;
import java.util.*;

class swea2477 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		//도착시간 순서대로 정렬하기.
		PriorityQueue<int[]> p=new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				if(o1[1]==o2[1]) return Integer.compare(o1[0],o2[0]);
				else return Integer.compare(o1[1], o2[1]);
			}
		});
		//접수완료 후 정비소 대기
		PriorityQueue<int[]> n=new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				if(o1[1]==o2[1]) return Integer.compare(o1[2],o2[2]);
				return Integer.compare(o1[1],o2[1]);
			}
		});
		for (int tc = 1; tc <= T; tc++) {
			int answer=0;
			st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			
			int a[]=new int[N+1];
			st=new StringTokenizer(br.readLine());
			for(int i=1;i<=N;i++) a[i]=Integer.parseInt(st.nextToken());
			
			int b[]=new int[M+1];
			st=new StringTokenizer(br.readLine());
			for(int i=1;i<=M;i++) b[i]=Integer.parseInt(st.nextToken());
			
			
			int c[]=new int[K+1];
			boolean v[]=new boolean[K+1];
			st=new StringTokenizer(br.readLine());
			for(int i=1;i<=K;i++) {
				c[i]=Integer.parseInt(st.nextToken());
			}
			
			int end=0;
			int prev[][]=new int[N+1][2];
			int next[][]=new int[M+1][3];
			int t=0;
			//큐 반복자
			Iterator<int[]> it;
			while(end<K) {
				for(int i=1;i<=K;i++) {
					if(!v[i]&&c[i]<=t) {
						v[i]=true;
						p.add(new int[] {i,c[i]});
					}
				}
				
				for(int i=1;i<=N;i++) {
					if(prev[i][0]==0&&p.size()>0) {
						int[] poll=p.poll();
						prev[i][0]=a[i];//남은 시간
						prev[i][1]=poll[0];//고객 번호
					}
				}
				for(int i=1;i<=N;i++) {
					if(prev[i][0]>0) {
						--prev[i][0];
						if(prev[i][0]==0) {
							int customer=prev[i][1];
							n.add(new int[] {customer,c[customer]+a[i],i});
						}
					}
				}
				for(int i=1;i<=M;i++) {
					if(next[i][0]==0&&n.size()>0) {
						int[] poll=n.poll();
						next[i][0]=b[i];//남은 시간
						next[i][1]=poll[0];//고객 번호
						next[i][2]=poll[2];//접수 번호
					}
				}
				for(int i=1;i<=M;i++) {
					if(next[i][0]>0) {
						--next[i][0];
						if(next[i][0]==0) {
							int customer=next[i][1];
							if(next[i][2]==A&&i==B) answer+=customer;
							++end;
						}
					}
				}
				it=p.iterator();
				while(it.hasNext()) {
					int[] tmp=it.next();
					if(c[tmp[0]]<=t) c[tmp[0]]++;
				}
				++t;
			}			
			if(answer==0) answer=-1;
			sb.append("#" + tc + " " + answer + "\n");
		}
		System.out.print(sb);
	}
}