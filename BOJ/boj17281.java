import java.util.*;
import java.io.*;

public class boj17281 {
	static int user[];
	static boolean visit[];
	static int n,answer;
	static int[][] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		arr=new int[n][10];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1;j<=9;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		user=new int[10];
		visit=new boolean[10];
		
		user[4]=1;
		visit[1]=true;
		answer=0;
		go(1);
		System.out.println(answer);
	}
	static void go(int v) {
		if(v==4) {
			go(v+1);
			return;
		}
		if(v==10) {
			int ret=Test();
			answer=answer<ret?ret:answer;
			return;
		}
		for(int i=2;i<=9;i++) {
			if(visit[i]) continue;
			visit[i]=true;
			user[v]=i;
			go(v+1);
			visit[i]=false;
		}
	}
	static int Test() {
		int ret=0;
		int inning=0;
		int out=0;
		int u=1;
		boolean map[]=new boolean[3];
		while(inning<n) {
			int cur=arr[inning][user[u]];
			switch(cur) {
			case 0:
				++out;
				break;
			case 1:
				if(map[2]) ++ret;
				map[2]=map[1];
				map[1]=map[0];
				map[0]=true;
				break;
			case 2:
				for(int i=1;i<=2;i++) if(map[i]) ++ret;
				map[2]=map[0];
				map[1]=true;
				map[0]=false;
				break;
			case 3:
				for(int i=0;i<=2;i++) if(map[i]) ++ret;
				map[2]=true;
				map[1]=false;
				map[0]=false;
				break;
			case 4:
				for(int i=0;i<=2;i++) {
					if(map[i]) ++ret;
					map[i]=false;
				}
				++ret;
				break;
			}
			//다음 타자
			++u;

			if(u==10) u=1;
			if(out==3) {
				for(int i=0;i<=2;i++) map[i]=false;
				++inning;
				out=0;
			}
		}
		return ret;
	}
}