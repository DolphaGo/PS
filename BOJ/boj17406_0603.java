import java.util.*;
import java.io.*;

public class boj17406_0603 {
	static int h,w,k,answer;
	static int map[][],arr[][],turn[];
	static boolean visit[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
		arr=new int[k][3];
		visit=new boolean[k];
		turn=new int[k];
		for(int i=0;i<k;i++) {
			st=new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken())-1;
			arr[i][1]=Integer.parseInt(st.nextToken())-1;
			arr[i][2]=Integer.parseInt(st.nextToken());
		}
		answer=Integer.MAX_VALUE;
		go(0);
		System.out.println(answer);
	}
	static void go(int v) {
		if(v==k) {
			int ret=Test();
			answer=answer>ret?ret:answer;
			return;
		}
		for(int i=0;i<k;i++) {
			if(visit[i]) continue;
			visit[i]=true;
			turn[v]=i;
			go(v+1);
			visit[i]=false;
		}
	}
	static int Test() {
		int[][] tmap=new int[h][w];
		for(int y=0;y<h;y++) tmap[y]=map[y].clone();
		
		for(int i=0;i<k;i++) {
			int now=turn[i];
			int r=arr[now][0];
			int c=arr[now][1];
			int s=arr[now][2];
			while(s>0) rotate(tmap,r,c,s--);
		}
		int ret=Integer.MAX_VALUE;
		for(int y=0;y<h;y++) {
			int sum=0;
			for(int x=0;x<w;x++) {
				sum+=tmap[y][x];
			}
			ret=ret>sum?sum:ret;
		}
		return ret;
	}
	static void rotate(int[][] map,int r,int c,int s) {
		int temp=map[r-s][c-s];
		for(int y=r-s;y<r+s;y++) map[y][c-s]=map[y+1][c-s];
		for(int x=c-s;x<c+s;x++) map[r+s][x]=map[r+s][x+1];
		for(int y=r+s;y>r-s;y--) map[y][c+s]=map[y-1][c+s];
		for(int x=c+s;x>c-s+1;x--) map[r-s][x]=map[r-s][x-1];
		map[r-s][c-s+1]=temp;
	}
}