import java.util.*;
import java.io.*;

public class Main {
	static class loc{
		int y;
		int x;
		loc(int y,int x){
			this.y=y;
			this.x=x;
		}
	}
	static class info{
		loc[] l;
		int cur;
		int shape;
		info(loc[] l,int cur,int shape) {
			this.l=new loc[3];
			for(int i=0;i<3;i++) this.l[i]=new loc(l[i].y,l[i].x);
			this.cur=cur;
			this.shape=shape;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		char map[][]=new char[n][n];
		
		int[][] tree=new int[3][2];
		int[][] aim=new int[3][2];
		int tidx=0;
		int aidx=0;
		for(int y=0;y<n;y++) {
			map[y]=br.readLine().toCharArray();
			for(int x=0;x<n;x++) {
				if(map[y][x]=='B') {
					tree[tidx][0]=y;
					tree[tidx++][1]=x;
				}else if(map[y][x]=='E') {
					aim[aidx][0]=y;
					aim[aidx++][1]=x;
				}
			}
		}
		int dy[]= {-1,1,0,0};
		int dx[]= {0,0,1,-1};
		boolean v[][][]=new boolean[n][n][2];
		Queue<info> q=new LinkedList<>();
		loc start[]=new loc[3];
		for(int i=0;i<3;i++) start[i]=new loc(tree[i][0],tree[i][1]);
		int shape=0; // ㅡ모양이면 0, ㅣ모양이면 1
		if(tree[0][0]<tree[1][0]) shape=1;
		v[tree[1][0]][tree[1][1]][shape]=true;
		info qs=new info(start,0,shape);
		q.add(qs);
		int answer=0;
		while(!q.isEmpty()) {
			info in=q.poll();
			loc[] p=in.l;
			loc c=p[1];
			int curshape=in.shape;
			//검사
			int cnt=0;
			for(int i=0;i<3;i++) {
				loc now=p[i];
				if(map[now.y][now.x]=='E') cnt++;
			}
			if(cnt==3) {
				answer=in.cur;
				break;
			}
			for(int i=0;i<4;i++) {
				boolean flag=true;
				for(int j=0;j<3;j++) {
					int ny=p[j].y+dy[i];
					int nx=p[j].x+dx[i];
					if(ny<0||nx<0||ny>=n||nx>=n||map[ny][nx]=='1') flag=false;
				}
				if(flag&&!v[c.y+dy[i]][c.x+dx[i]][curshape]) {
					v[c.y+dy[i]][c.x+dx[i]][curshape]=true;
					loc next[]=new loc[3];
					for(int j=0;j<3;j++) {
						int ny=p[j].y+dy[i];
						int nx=p[j].x+dx[i];
						next[j]=new loc(ny,nx);
					}
					q.add(new info(next,in.cur+1,curshape));
				}
			}
			boolean rotatePossible=true;
			for(int yy=c.y-1;yy<=c.y+1;yy++) {
				for(int xx=c.x-1;xx<=c.x+1;xx++) {
					if(yy<0||xx<0||yy>=n||xx>=n||map[yy][xx]=='1') {
						rotatePossible=false;
						break;
					}
				}
				if(!rotatePossible) break;
			}
			if(rotatePossible&&!v[c.y][c.x][1-curshape]) {
				//시계방향으로 돌릴게
				v[c.y][c.x][1-curshape]=true;
				loc next[]=new loc[3];
				next[1]=new loc(c.y,c.x);
				if(p[0].x<c.x) {// ㅡ 모양이라면
					next[0]=new loc(c.y-1,c.x);
					next[2]=new loc(c.y+1,c.x);
				}else { //ㅣ 모양이라면
					next[0]=new loc(c.y,c.x-1);
					next[2]=new loc(c.y,c.x+1);
				}
				q.add(new info(next,in.cur+1,1-curshape));
			}
		}
		System.out.println(answer);
	}
}