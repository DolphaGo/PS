import java.util.*;
import java.io.*;

public class Main {
	static int[][] memo,cost;
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());
		cost=new int[n][n];
		for(int y=0;y<n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<n;x++) {
				cost[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		memo=new int[n][1<<n];
		for(int i=0;i<n;i++) Arrays.fill(memo[i], Integer.MAX_VALUE/2);
		System.out.println(TSP(0,1));
	}
	static int TSP(int cur,int visit) {
		
		if(visit==(1<<n)-1) return cost[cur][0]==0?Integer.MAX_VALUE/2:cost[cur][0];
		if(memo[cur][visit]!=Integer.MAX_VALUE/2) return memo[cur][visit];
		
		for(int i=0;i<n;i++) {
			if((visit&(1<<i))>0) continue;
			if(cost[cur][i]==0) continue;
			memo[cur][visit]=Math.min(memo[cur][visit], TSP(i,visit|(1<<i))+cost[cur][i]);
		}
		return memo[cur][visit];
	}
}
