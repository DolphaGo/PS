import java.util.*;
import java.io.*;

public class boj13460_0606 {
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static int answer,h,w;
	static char[][] map;
	static boolean[][][][] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		h=Integer.parseInt(st.nextToken());
		w=Integer.parseInt(st.nextToken());
		map=new char[h][w];
		visit=new boolean[h][w][h][w];
		int red[]=new int[3];
		int blue[]=new int[3];
		for(int y=0;y<h;y++) {
			map[y]=br.readLine().toCharArray();
			for(int x=0;x<w;x++) {
				if(map[y][x]=='R') {
					red[0]=y;
					red[1]=x;
					map[y][x]='.';
				}else if(map[y][x]=='B') {
					blue[0]=y;
					blue[1]=x;
					map[y][x]='.';
				}
			}
		}
		answer=Integer.MAX_VALUE;
		visit[red[0]][red[1]][blue[0]][blue[1]]=true;
		go(red,blue,0);
		System.out.println(answer==Integer.MAX_VALUE?-1:answer);
	}
	static void go(int[] red,int[] blue,int cnt) {
		if(red[2]==1&&blue[2]!=1) {
			answer=answer>cnt?cnt:answer;
			return;
		}else if(blue[2]==1) return;
		if(cnt>=10) return;
		
		int[] nred=new int[3];
		int[] nblue=new int[3];
		for(int i=0;i<4;i++) {
			nred=red.clone();
			nblue=blue.clone();
			while(isRange(nred)) {
				nred[0]+=dy[i];
				nred[1]+=dx[i];
			}
			while(isRange(nblue)) {
				nblue[0]+=dy[i];
				nblue[1]+=dx[i];
			}
			
			if(map[nred[0]][nred[1]]=='O') {
				nred[2]=1;
			}else {
				nred[0]-=dy[i];
				nred[1]-=dx[i];
			}
			
			if(map[nblue[0]][nblue[1]]=='O') {
				nblue[2]=1;
			}else {
				nblue[0]-=dy[i];
				nblue[1]-=dx[i];
			}
			//둘다 구멍에 빠지지 않았을 때
			if(nblue[2]!=1&&nred[2]!=1) {
				if(nblue[0]==nred[0]&&nblue[1]==nred[1]) {
					int rdis=getdis(red,nred);
					int bdis=getdis(blue,nblue);
					if(rdis>bdis) {
						nred[0]-=dy[i];
						nred[1]-=dx[i];
					}else {
						nblue[0]-=dy[i];
						nblue[1]-=dx[i];
					}
				}
			}
			if(!visit[nred[0]][nred[1]][nblue[0]][nblue[1]]) {
				visit[nred[0]][nred[1]][nblue[0]][nblue[1]]=true;
				go(nred,nblue,cnt+1);
				visit[nred[0]][nred[1]][nblue[0]][nblue[1]]=false;
			}
		}
		
	}
	static boolean isRange(int[] a) {
		if(a[0]>=1&&a[1]>=1&&a[0]<h-1&&a[1]<w-1&&map[a[0]][a[1]]=='.') return true;
		return false;
	}
	static int getdis(int[] a,int[] b) {
		return Math.abs(a[0]-b[0])+Math.abs(a[1]-b[1]);
	}
}