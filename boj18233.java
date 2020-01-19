import java.util.*;
import java.io.*;

public class Main {
	static int n,p,e;
	static int[][] lim;
	static int[] who;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		p=Integer.parseInt(st.nextToken());
		e=Integer.parseInt(st.nextToken());
		lim=new int[n][2];
		who=new int[p];
		gives=new int[n];
		answer=new int[p];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			lim[i][0]=Integer.parseInt(st.nextToken());
			lim[i][1]=Integer.parseInt(st.nextToken());
		}
		flag=false;
		go(0,0);
		if(!flag) System.out.println(-1);
	}
	static boolean flag;
	static int answer[];
	static int gives[];
	static boolean spread() {
		int give=0;
		Arrays.fill(gives, 0);
		for(int i=0;i<p;i++) {
			int cur=who[i];
			int mid=(lim[cur][0]+lim[cur][1])/2;
			gives[cur]=mid;
			give+=mid;
		}
		if(give<e) {
			for(int i=0;i<p;i++) {
				int cur=who[i];
				while(give<e&&gives[cur]<lim[cur][1]) {
					++gives[cur];
					++give;
				}
			}
		}else if(give>e) {
			for(int i=0;i<p;i++) {
				int cur=who[i];
				while(give>e&&lim[cur][0]<gives[cur]) {
					--gives[cur];
					--give;
				}
			}
		}
		
		if(give==e) {
			flag=true;
			for(int i=0;i<n;i++) {
				System.out.print(gives[i]+" ");
			}
		}
		return flag;
	}
	static void go(int v,int idx) {
		if(v==p) {
			if(spread()) System.exit(0);
			return;
		}
		if(idx==n) return;
		who[v]=idx;
		go(v+1,idx+1);
		go(v,idx+1);
	}
}