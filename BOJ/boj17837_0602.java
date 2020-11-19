import java.io.*;
import java.util.*;

public class boj17837_0602 {
	static class Horse{
		int id,y,x,d;
		
		Horse(int id,int y,int x,int d){
			this.id=id;
			this.y=y;
			this.x=x;
			this.d=d;
		}
	}
	static int n;
	static int map[][];
	static Deque<Horse> hmap[][];
	static TreeMap<Integer,Horse> list=new TreeMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		map=new int[n][n];
		hmap=new Deque[n][n];
		for(int y=0;y<n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<n;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
				hmap[y][x]=new ArrayDeque<>();
			}
		}
		
		
		for(int i=1;i<=k;i++) {
			st=new StringTokenizer(br.readLine());
			int y=Integer.parseInt(st.nextToken())-1;
			int x=Integer.parseInt(st.nextToken())-1;
			int d=Integer.parseInt(st.nextToken())-1;
			Horse h=new Horse(i,y,x,d);
			hmap[y][x].addLast(h);
			list.put(i,h);
		}
		
		int t=0;
		while(true) {
			t++;
			if(t>1000) {
				System.out.println(-1);
				return;
			}
			if(go()) break;
		}
		System.out.println(t);
	}
	static int dy[]= {0,0,-1,1};
	static int dx[]= {1,-1,0,0};
	static boolean go() {
		for(int i:list.keySet()) {
			Horse cur=list.get(i);
			int id=i;
			int y=cur.y;
			int x=cur.x;
			int ny=y+dy[cur.d];
			int nx=x+dx[cur.d];
			if(ny<0||nx<0||ny==n||nx==n||map[ny][nx]==2) {
				if(cur.d<=1) cur.d=1-cur.d;
				else cur.d=5-cur.d;
				
				ny=y+dy[cur.d];
				nx=x+dx[cur.d];
				if(ny<0||nx<0||ny==n||nx==n||map[ny][nx]==2) {}
				else if(map[ny][nx]==1) {//빨간색
					while(hmap[y][x].size()>0&&hmap[y][x].getLast().id!=id) {
						Horse h=hmap[y][x].pollLast();
						h.y=ny;
						h.x=nx;
						list.replace(h.id, h);
						hmap[ny][nx].addLast(h);
					}
					Horse h=hmap[y][x].pollLast();
					h.y=ny;	h.x=nx;
					list.replace(h.id, h);
					hmap[ny][nx].addLast(h);
				}else {//하얀색
					Stack<Horse> stack=new Stack<>();
					while(hmap[y][x].size()>0&&hmap[y][x].getLast().id!=id) {
						Horse h=hmap[y][x].pollLast();
						h.y=ny;
						h.x=nx;
						list.replace(h.id, h);
						stack.push(h);
					}
					Horse h=hmap[y][x].pollLast();
					h.y=ny;	h.x=nx;
					list.replace(h.id, h);
					hmap[ny][nx].addLast(h);
					while(!stack.isEmpty()) {
						hmap[ny][nx].addLast(stack.pop());
					}
				}
				
			}else if(map[ny][nx]==1) {//빨간색
				while(hmap[y][x].size()>0&&hmap[y][x].getLast().id!=id) {
					Horse h=hmap[y][x].pollLast();
					h.y=ny;
					h.x=nx;
					list.replace(h.id, h);
					hmap[ny][nx].addLast(h);
				}
				Horse h=hmap[y][x].pollLast();
				h.y=ny;	h.x=nx;
				list.replace(h.id, h);
				hmap[ny][nx].addLast(h);
			}else {//하얀색
				Stack<Horse> stack=new Stack<>();
				while(hmap[y][x].size()>0&&hmap[y][x].getLast().id!=id) {
					Horse h=hmap[y][x].pollLast();
					h.y=ny;
					h.x=nx;
					list.replace(h.id, h);
					stack.push(h);
				}
				Horse h=hmap[y][x].pollLast();
				h.y=ny;	h.x=nx;
				list.replace(h.id, h);
				hmap[ny][nx].addLast(h);
				while(!stack.isEmpty()) {
					hmap[ny][nx].addLast(stack.pop());
				}
			}
			for(int yy=0;yy<n;yy++) {
				for(int xx=0;xx<n;xx++) {
					if(hmap[yy][xx].size()>=4) return true;
				}
			}
		}
		return false;
	}
}