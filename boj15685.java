import java.io.*;
import java.util.*;
class boj15685 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		boolean map[][]=new boolean[101][101];
		int dy[]= {0,-1,0,1};
		int dx[]= {1,0,-1,0};
		
		int n=Integer.parseInt(br.readLine());
		ArrayList<Integer> list=new ArrayList<>();
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			int g=Integer.parseInt(st.nextToken());
			list.clear();
			list.add(d);
			map[y][x]=true;
			for(int j=0;j<g;j++) {
				int size=list.size();
				for(int k=size-1;k>=0;k--) {
					int dir=list.get(k);
					list.add((dir+1)%4);
				}
			}
			for(int k=0;k<list.size();k++) {
				int dir=list.get(k);
				y+=dy[dir];
				x+=dx[dir];
				map[y][x]=true;
			}
		}
		int answer=0;
		for(int y=0;y<100;y++) {
			for(int x=0;x<100;x++) {
				if(map[y][x]&&map[y+1][x]&&map[y][x+1]&&map[y+1][x+1]) ++answer;
			}
		}
		System.out.println(answer);
		
	}
	static int pow(int a,int b) {
		return b==0?1:a*pow(a,b-1);
	}
}