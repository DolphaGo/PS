import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int answer=0;
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			char map[][] = new char[h][w];
			boolean visit[][]=new boolean[h][w];
			for (int y = 0; y < h; y++) {
				map[y] = br.readLine().toCharArray();
			}
			String keyString = br.readLine();
			boolean keys[] = new boolean[26];
			if (keyString.charAt(0) != '0') {
				for (int i = 0; i < keyString.length(); i++) {
					keys[keyString.charAt(i) - 'a'] = true;
				}
			}
			
			Queue<int[]> q=new LinkedList<int[]>();
			Queue<int[]> keep=new LinkedList<int[]>();
			
			for(int y=0;y<h;y++) {
				for(int x=0;x<w;x+=w-1) {
					if(map[y][x]=='*') continue;
					visit[y][x]=true;
					if('a'<=map[y][x]&&map[y][x]<='z') {
						keys[map[y][x]-'a']=true;
						q.add(new int[] {y,x});
					}
					else if('A'<=map[y][x]&&map[y][x]<='Z') {
						keep.add(new int[] {y,x});
					}
					else if(map[y][x]=='.'){
						q.add(new int[] {y,x});
					}else {
                        			q.add(new int[]{y,x});
						answer++;
					}
				}
			}
			for(int x=1;x<w-1;x++) {
				for(int y=0;y<h;y+=h-1) {
					if(map[y][x]=='*') continue;
                   			visit[y][x]=true;
					if('a'<=map[y][x]&&map[y][x]<='z') {
						keys[map[y][x]-'a']=true;
						q.add(new int[] {y,x});
					}
					else if('A'<=map[y][x]&&map[y][x]<='Z') {
						keep.add(new int[] {y,x});
					}
					else if(map[y][x]=='.'){
						q.add(new int[] {y,x});
					}else {
                        			q.add(new int[]{y,x});
						answer++;
					}
				}
			}
			int dy[]= {-1,1,0,0};
			int dx[]= {0,0,1,-1};

			int size=keep.size();
			for(int i=0;i<size;i++) {
				int[] p=keep.poll();
				if(keys[map[p[0]][p[1]]-'A']) q.add(p);
				else keep.add(p);
			}

			
			while(!q.isEmpty()) {
				
				while(!q.isEmpty()) {
					int[] p=q.poll();
					
					for(int i=0;i<4;i++) {
						int ny=p[0]+dy[i];
						int nx=p[1]+dx[i];
						if(ny>=0&&nx>=0&&ny<h&&nx<w&&map[ny][nx]!='*'&&!visit[ny][nx]) {
							visit[ny][nx]=true;
							if('a'<=map[ny][nx]&&map[ny][nx]<='z') {
								keys[map[ny][nx]-'a']=true;
								q.add(new int[] {ny,nx});
							}
							else if('A'<=map[ny][nx]&&map[ny][nx]<='Z') {
								if(keys[map[ny][nx]-'A']) q.add(new int[] {ny,nx});
								else keep.add(new int[] {ny,nx});
							}
							else if(map[ny][nx]=='.') q.add(new int[] {ny,nx});
							else{
                                				q.add(new int[]{ny,nx});
                                    				answer++;
                            				} 
						}
					}
				}
				
				size=keep.size();
				for(int i=0;i<size;i++) {
					int[] p=keep.poll();
					if(keys[map[p[0]][p[1]]-'A']) q.add(p);
					else keep.add(p);
				}
			}
			
			System.out.println(answer);
			keep.clear();
		}
	}
}
