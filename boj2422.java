import java.util.*;
import java.io.*;
public class boj2422 {
	static int n,m,answer;
	static ArrayList<Integer> list[];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		list=new ArrayList[n+1];
		for(int i=1;i<=n;i++) list[i]=new ArrayList<Integer>();
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		answer=0;
		go(0,0);
		System.out.println(answer);
	}
	static int temp[]=new int[3];
	static void go(int v,int cnt) {
		if(cnt==3) {
			boolean flag=true;
			loop:for(int i=0;i<3;i++) {
				int cur=temp[i];
				for(int val:list[cur]) {
					for(int j=0;j<3;j++) {
						if(i==j) continue;
						int ncur=temp[j];
						if(ncur==val) {
							flag=false;
							break loop;
						}
					}
				}
			}
			if(flag) ++answer;
			return;
		}
		if(v==n) return;
		temp[cnt]=v+1;
		go(v+1,cnt+1);
		go(v+1,cnt);
	}
}
