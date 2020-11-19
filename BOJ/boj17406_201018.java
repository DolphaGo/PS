import java.util.*;
import java.io.*;
public class Main {
	static int h,w,k,answer;
	static int[][] map;
	static int[][] command;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		h=Integer.parseInt(st.nextToken());
		w=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		map=new int[h][w];
		for(int y=0;y<h;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<w;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		
		command=new int[k][3];
		for(int i=0;i<k;i++) {
			st=new StringTokenizer(br.readLine());
			command[i][0]=Integer.parseInt(st.nextToken())-1;
			command[i][1]=Integer.parseInt(st.nextToken())-1;
			command[i][2]=Integer.parseInt(st.nextToken());
		}
		answer=Integer.MAX_VALUE;
		visit=new boolean[k];
		arr=new int[k];
		go(0);
		System.out.println(answer);
	}
	static int[] arr;
	static void go(int v) {
		if(v==k) {
			int[][] temp=new int[h][w];
			for(int y=0;y<h;y++) {
				for(int x=0;x<w;x++) {
					temp[y][x]=map[y][x];
				}
			}
			
			int res=simulation(temp);
			answer=Math.min(answer, res);
			return;
		}
		for(int i=0;i<k;i++) {
			if(visit[i]) continue;
			visit[i]=true;
			arr[v]=i;
			go(v+1);
			visit[i]=false;
		}
	}
	static int simulation(int[][] temp) {
		int res=Integer.MAX_VALUE;
		for(int i=0;i<k;i++) {
			int[] cur=command[arr[i]];
			int sy=cur[0];
			int sx=cur[1];
			int ss=cur[2];
			for(int j=1;j<=ss;j++) {
				//[sy-j,sx-j]~[sy+j,sx+j] 까지
				int val=temp[sy-j][sx-j];
				for(int y=sy-j;y<sy+j;y++) temp[y][sx-j]=temp[y+1][sx-j];
				for(int x=sx-j;x<sx+j;x++) temp[sy+j][x]=temp[sy+j][x+1];
				for(int y=sy+j;y>sy-j;y--) temp[y][sx+j]=temp[y-1][sx+j];
				for(int x=sx+j;x>sx-j;x--) temp[sy-j][x]=temp[sy-j][x-1];
				temp[sy-j][sx-j+1]=val;
			}
		}
		for(int y=0;y<h;y++) {
			int sum=0;
			for(int x=0;x<w;x++) {
				sum+=temp[y][x];
			}
			res=Math.min(res, sum);
		}
		return res;
	}
}

