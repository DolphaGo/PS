import java.util.*;
import java.io.*;
public class boj6497{
	public static void main(String[] args) throws IOException{
		//객체는 한번 선언하고 테스트케이스에서 모두 사용합니다.
		StringBuilder sb=new StringBuilder();
		PriorityQueue<int[]> pq=new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				return Integer.compare(o1[2],o2[2]);
			}
		});
		//입력
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int m=Integer.parseInt(st.nextToken());
		int n=Integer.parseInt(st.nextToken());
		while(!(m==0&&n==0)) {
			int answer=0;
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				int z=Integer.parseInt(st.nextToken());
				pq.add(new int[] {x,y,z});
				answer+=z; //모든 길의 합을 구해놓는다.
			}
			
			int[] p=new int[m];
			for(int i=0;i<m;i++) p[i]=i;
			
			int use=0;
			int conn=0;
			while(conn<m-1) {
				int[] po=pq.poll();
				int x=po[0];
				int y=po[1];
				x=find(p,x);
				y=find(p,y);
				//서로 연결이 되어 있지 않다면
				if(x!=y) {
					++conn;//이 길은 추가해줍니다.
					use+=po[2]; // 해당 비용을 더해줍니다.
					if(x>y) p[x]=y;
					else p[y]=x;
				}
			}
			answer-=use; //전체 비용-(최소한 사용한 비용) = 최대 절약 비용
			sb.append(answer+"\n");
			st=new StringTokenizer(br.readLine());
			m=Integer.parseInt(st.nextToken());
			n=Integer.parseInt(st.nextToken());
			pq.clear();
		}
		System.out.print(sb);
	}
	public static int find(int[] p,int a) {
		if(p[a]==a) return a;
		return p[a]=find(p,p[a]);
	}
}