import java.io.*;
import java.util.*;

public class boj16236{
	static int n;
	static int[][] map;
	static boolean[][] visit;
	public static void main(String[] args) throws IOException{
	  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	  StringTokenizer st;
	  n=Integer.parseInt(br.readLine());
	  map=new int[n][n];
	  visit=new boolean[n][n];
	  int sy=0;
	  int sx=0;
	  for(int y=0;y<n;y++) {
		  st=new StringTokenizer(br.readLine());
		  for(int x=0;x<n;x++) {
			  map[y][x]=Integer.parseInt(st.nextToken());
			  if(map[y][x]==9) {
				  map[y][x]=0;
				  sy=y; sx=x;
			  }
		  }
	  }

	  //상어의 가장 가까운 먹잇감을 저장할 ArrayList 선언
	  ArrayList<int[]> list=new ArrayList<int[]>();
	  int dy[]= {-1,1,0,0};
	  int dx[]= {0,0,-1,1};
	  
	  Queue<int[]> q=new LinkedList<int[]>();
	  int t=0;
	  int size=2;
	  int eat=0;
	  while(true) {
		  //아기 상어 현재 위치로부터 먹잇감을 탐색하기
		  //상어위치,거리
		  q.add(new int[] {sy,sx,0});
		  visit[sy][sx]=true;
		  int min=Integer.MAX_VALUE;
		  while(!q.isEmpty()) {
			  int[] p=q.poll();
			  int y=p[0];
			  int x=p[1];
			  int len=p[2];
			  if(len>min) continue;
			  for(int i=0;i<4;i++) {
				  int ny=y+dy[i];
				  int nx=x+dx[i];
				  if(ny>=0&&nx>=0&&ny<n&&nx<n&&!visit[ny][nx]&&map[ny][nx]<=size) {
					  visit[ny][nx]=true;
					  if(map[ny][nx]!=0&&map[ny][nx]<size&&len+1<=min) {
						  //먹을 수 있는 크기라면
						  //먹을 리스트에 넣어두고 탐색 종료.
						  min=min>len+1?len+1:min;
						  list.add(new int[] {ny,nx,len+1});
					  }else {
						  //그냥 지나다닐 수 있는 상대라면
						  q.add(new int[] {ny,nx,len+1});
					  }
				  }
			  }
		  }
		  
		  
		  //먹을 것이 있다면
		  if(list.size()>0) {
			  Collections.sort(list,new Comparator<int[]>() {
				  public int compare(int[] o1,int[] o2) {
					  //y축의 위치가 같다면 왼쪽 정렬
					  if(o1[0]==o2[0]) return Integer.compare(o1[1],o2[1]);
					  //Default는 가장 위쪽 물고기
					  return Integer.compare(o1[0],o2[0]);
				  }
			  });
			  int select[]=list.get(0);
			  //먹은 것으로 처리
			  map[select[0]][select[1]]=0;
			  //가는데 걸린 시간
			  t+=select[2];
			  //리스트 초기화 및 방문배열 초기화
			  list.clear();
			  for(int i=0;i<n;i++) Arrays.fill(visit[i], false);
			  //상어 정보 업데이트
			  sy=select[0];
			  sx=select[1];
			  eat+=1;
			  if(eat==size) {
				  eat=0;
				  ++size;
			  }
		  }else break;
	  }
	  System.out.println(t);
  }
}
