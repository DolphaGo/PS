import java.io.*;
import java.util.*;

class boj17143{
	static int h,w;
	static int map[][],newmap[][];
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,1,-1};
	static HashMap<Integer, Info> hash;
	static Queue<Integer> delete;
	static class Info{
		int r,c,s,d,z;
		Info(int r,int c,int s,int d,int z){
			this.r=r;
			this.c=c;
			this.s=s;
			this.d=d;
			this.z=z;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		h=Integer.parseInt(st.nextToken());
		w=Integer.parseInt(st.nextToken());
		map=new int[h+1][w+1];
		int m=Integer.parseInt(st.nextToken());
		hash=new HashMap<Integer, Info>();
		delete=new LinkedList<Integer>();
		for(int i=1;i<=m;i++) {
			st=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken())-1;
			int z=Integer.parseInt(st.nextToken());
			if(d==0||d==1) s%=(2*h-2);
			else s%=(2*w-2);
			Info info=new Info(r,c,s,d,z);
			hash.put(i,info);
			map[r][c]=i;
		}
		int answer=0;
		for(int i=1;i<=w;i++) {
			//낚시
			for(int y=1;y<=h;y++) {
				if(map[y][i]!=0) {
					answer+=hash.get(map[y][i]).z;
					hash.remove(map[y][i]);
					map[y][i]=0;
					break;
				}
			}
			//이동
			newmap=new int[h+1][w+1];
			for(int key:hash.keySet()) {
				Info now=hash.get(key);
				boolean flag=move(key,now);
				Info next=hash.get(key);
				if(flag) newmap[next.r][next.c]=key;
				else delete.add(key);
			}

			while(!delete.isEmpty()) {
				hash.remove(delete.poll());
			}
			for(int y=1;y<=h;y++) {
				map[y]=newmap[y].clone();
			}
		}
		System.out.println(answer);
	}
	static boolean move(int key,Info now) {
		int s=now.s;
		int z=now.z;
		int d=now.d;
		int r=now.r;
		int c=now.c;
		while(s-->0) {
			r+=dy[d];
			c+=dx[d];
			if(r>h) {
				r=h-1;
				d=0;
			}else if(c>w) {
				c=w-1;
				d=3;
			}else if(r==0) {
				r=2;
				d=1;
			}else if(c==0){
				c=2;
				d=2;
			}
		}
		Info newInfo=new Info(r,c,now.s,d,now.z);
		hash.replace(key, newInfo);
		
		//다른 상어가 없으면 OK
		if(newmap[r][c]==0) return true;
		
		//다른 상어가 있는데, 자기보다 크면 빼버리고
		if(hash.get(newmap[r][c]).z>z) return false;
		else {
			//자기가 더 크면 다른 상어를 없애버리기.
			delete.add(newmap[r][c]);
			return true;
		}
	}
}