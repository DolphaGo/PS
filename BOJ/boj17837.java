import java.util.*;
import java.io.*;

public class boj17837 {
	static int dy[]= {0,0,-1,1};
	static int dx[]= {1,-1,0,0};
	static int N,K;
	static int map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		map=new int[N][N];
		for(int y=0;y<N;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<N;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		
		ArrayList<Integer> arr[][]=new ArrayList[N][N];
		for(int y=0;y<N;y++) {
			for(int x=0;x<N;x++) {
				arr[y][x]=new ArrayList<Integer>();
			}
		}
		
		int horse[][]=new int[K][3];
		for(int i=0;i<K;i++) {
			st=new StringTokenizer(br.readLine());
			horse[i][0]=Integer.parseInt(st.nextToken())-1;
			horse[i][1]=Integer.parseInt(st.nextToken())-1;
			horse[i][2]=Integer.parseInt(st.nextToken())-1;
			arr[horse[i][0]][horse[i][1]].add(i);
		}
		int turn=0;
		boolean flag=false;
		loop:while(true) {
			++turn;
			if(turn>1000) break;
			for(int i=0;i<K;i++) {
				int y=horse[i][0];
				int x=horse[i][1];
				int dir=horse[i][2];
				int ny=y+dy[dir];
				int nx=x+dx[dir];
				if(ny<0||nx<0||ny>=N||nx>=N||map[ny][nx]==2) {
					//방향 바꾸고
					int new_dir=0;
					if(dir==0||dir==1) new_dir=1-dir;
					else new_dir=5-dir;
					int nny=y+dy[new_dir];
					int nnx=x+dx[new_dir];
					
					if(nny<0||nnx<0|nny>=N||nnx>=N||map[nny][nnx]==2) {
						horse[i][2]=new_dir;
					}else if(map[nny][nnx]==1) {
						int idx=arr[y][x].indexOf(i);
						int len=arr[y][x].size()-idx;
						for(int iter=0;iter<len;iter++) {
							int First=arr[y][x].get(arr[y][x].size()-1);
							//말의 정보 업데이트
							horse[First][0]=nny;
							horse[First][1]=nnx;
							
							//맵 업데이트
							arr[nny][nnx].add(First);
							arr[y][x].remove(arr[y][x].size()-1);
						}
						//방향은 따로 업데이트
						horse[i][2]=new_dir;
					}else {
						int idx=arr[y][x].indexOf(i);
						int len=arr[y][x].size()-idx;
						for(int iter=0;iter<len;iter++) {
							int First=arr[y][x].get(idx);
							//말의 정보 업데이트
							horse[First][0]=nny;
							horse[First][1]=nnx;
							
							//맵 업데이트
							arr[nny][nnx].add(First);
							arr[y][x].remove(idx);
						}
						//방향은 따로 업데이트
						horse[i][2]=new_dir;
					}
				}else if(map[ny][nx]==1) {
					int idx=arr[y][x].indexOf(i);
					int len=arr[y][x].size()-idx;
					for(int iter=0;iter<len;iter++) {
						int First=arr[y][x].get(arr[y][x].size()-1);
						//말의 정보 업데이트
						horse[First][0]=ny;
						horse[First][1]=nx;
						
						//맵 업데이트
						arr[ny][nx].add(First);
						arr[y][x].remove(arr[y][x].size()-1);
					}
					//방향은 따로 업데이트
					horse[i][2]=dir;
				}else {
					int idx=arr[y][x].indexOf(i);
					int len=arr[y][x].size()-idx;
					for(int iter=0;iter<len;iter++) {
						int First=arr[y][x].get(idx);
						//말의 정보 업데이트
						horse[First][0]=ny;
						horse[First][1]=nx;
						
						//맵 업데이트
						arr[ny][nx].add(First);
						arr[y][x].remove(idx);
					}
					//방향은 따로 업데이트
					horse[i][2]=dir;
				}
				
				//4개이상 겹쳐진 것이 있는지 확인
				for(int yy=0;yy<N;yy++) {
					for(int xx=0;xx<N;xx++) {
						if(arr[yy][xx].size()>=4) {
							flag=true;
							break loop;
						}
					}
				}
			}
		}
		System.out.println(flag?turn:-1);
	}
}
