import java.io.*;
import java.util.*;

class boj17143_0525{
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,1,-1};
	static class fish{
		int r,c,s,d,z;
		fish(int r,int c,int s,int d,int z){
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
		int h=Integer.parseInt(st.nextToken());
		int w=Integer.parseInt(st.nextToken());
		fish map[][]=new fish[h+1][w+1];
		PriorityQueue<fish> tmp[][]=new PriorityQueue[h+1][w+1];
		for(int y=1;y<=h;y++) {
			for(int x=1;x<=w;x++) {
				tmp[y][x]=new PriorityQueue<>(new Comparator<fish>() {
					public int compare(fish o1,fish o2) {
						return Integer.compare(o2.z,o1.z);
					}
				});
			}
		}
		int m=Integer.parseInt(st.nextToken());
		for(int i=1;i<=m;i++) {
			st=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken())-1;
			int z=Integer.parseInt(st.nextToken());
			if(d==0||d==1) s%=(2*h-2);
			else s%=(2*w-2);
			map[r][c]=new fish(r,c,s,d,z);
		}
		int answer=0;
		for(int i=1;i<=w;i++) {
			//낚시
			for(int y=1;y<=h;y++) {
				if(map[y][i]!=null) {
					answer+=map[y][i].z;
					map[y][i]=null;
					break;
				}
			}
			//이동
			for(int y=1;y<=h;y++) {
				for(int x=1;x<=w;x++) {
					if(map[y][x]==null) continue;
					fish f=map[y][x];
					map[y][x]=null;
					int s=f.s;
					while(s-->0) {
						f.r+=dy[f.d];
						f.c+=dx[f.d];
						if(f.r>h) {
							f.r=h-1;
							f.d=0;
						}else if(f.c>w) {
							f.c=w-1;
							f.d=3;
						}else if(f.c==0) {
							f.c=2;
							f.d=2;
						}else if(f.r==0) {
							f.r=2;
							f.d=1;
						}
					}
					tmp[f.r][f.c].add(f);
				}
			}
			for(int y=1;y<=h;y++) {
				for(int x=1;x<=w;x++) {
					if(tmp[y][x].size()>0) {
						map[y][x]=tmp[y][x].poll();
						tmp[y][x].clear();
					}
				}
			}
		}
		System.out.println(answer);
	}
}