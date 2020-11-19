import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static char[][] map;
	static int [][] check;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		map=new char[n][n];
		check=new int[n][n];
		for(int y=0;y<n;y++) Arrays.fill(check[y], Integer.MAX_VALUE);
		check[0][0]=0;
		for(int y=0;y<n;y++) map[y]=br.readLine().toCharArray();
		Queue<int[]> q=new LinkedList<int[]>();
		q.add(new int[] {0,0});
		int dy[]= {-1,1,0,0};
		int dx[]= {0,0,1,-1};
		while(!q.isEmpty()) {
			int[] p=q.poll();
			for(int i=0;i<4;i++) {
				int ny=p[0]+dy[i];
				int nx=p[1]+dx[i];
				if(ny>=0&&nx>=0&&ny<n&&nx<n) {
					int val=check[p[0]][p[1]];
					int next=map[ny][nx]=='0'?val+1:val;
					if(next<check[ny][nx]) {
						check[ny][nx]=next;
						q.add(new int[] {ny,nx});
					}
				}
			}
		}
		System.out.println(check[n-1][n-1]);
	}
}