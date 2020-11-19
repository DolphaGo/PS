import java.io.*;
import java.util.*;

public class Main {
    static int n,m,t;
    static int[][] arr;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        t=Integer.parseInt(st.nextToken());
        
        arr=new int[n+1][m];
        for(int i=1;i<=n;i++) {
        	st=new StringTokenizer(br.readLine());
        	for(int j=0;j<m;j++) {
        		arr[i][j]=Integer.parseInt(st.nextToken());
        	}
        }
        //1~n 그리고 0~m-1까지의 숫자들
        for(int i=0;i<t;i++) {
        	st=new StringTokenizer(br.readLine());
        	int x=Integer.parseInt(st.nextToken());
        	int d=Integer.parseInt(st.nextToken());
        	int k=Integer.parseInt(st.nextToken());
        	if(d==0) clockwise(x,k);
        	else counterClockwise(x,k);
        	
        	List<int[]> result=bfs();
        	
        	if(result.size()>0) {
        		for(int[] p:result) {
        			arr[p[0]][p[1]]=0;
        		}
        	}else {
        		int sum=0;
        		int cnt=0;
        		for(int a=1;a<=n;a++) {
        			for(int b=0;b<m;b++) {
        				if(arr[a][b]>0) {
        					++cnt;
        					sum+=arr[a][b];
        				}
        			}
        		}
        		double avg=1.0*sum/cnt;
        		for(int a=1;a<=n;a++) {
        			for(int b=0;b<m;b++) {
        				if(arr[a][b]>0) {
        					if(arr[a][b]>avg) arr[a][b]-=1;
        					else if(arr[a][b]<avg) arr[a][b]+=1;
        				}
        			}
        		}
        	}
        }
        
        int answer=0;
        for(int y=1;y<=n;y++) {
        	for(int x=0;x<m;x++) {
        		answer+=arr[y][x];
        	}
        }
        System.out.println(answer);
    }
	static void clockwise(int x,int k) {
		for(int i=x;i<=n;i+=x) { //배수
			int[] newArr=new int[m];
			for(int j=0;j<m;j++) {
				int idx=(j+k)%m;
				newArr[idx]=arr[i][j];
			}
			
			for(int j=0;j<m;j++) {
				arr[i][j]=newArr[j];
			}
		}
	}
	static void counterClockwise(int x,int k) {
		for(int i=x;i<=n;i+=x) { //배수
			int[] newArr=new int[m];
			for(int j=0;j<m;j++) {
				int idx=(j-k+m)%m;
				newArr[idx]=arr[i][j];
			}
			
			for(int j=0;j<m;j++) {
				arr[i][j]=newArr[j];
			}
		}
	}
	static int dy[]= {-1,1,0,0};
	static int dx[]= {0,0,-1,1};
	static List<int[]> bfs(){
		List<int[]> list=new ArrayList<int[]>();
		Queue<int[]> q=new LinkedList<int[]>();
		Queue<int[]> tr=new LinkedList<int[]>();
		boolean[][] visit=new boolean[n+1][m];
		for(int y=1;y<=n;y++) {
			for(int x=0;x<m;x++) {
				if(arr[y][x]>0&&!visit[y][x]) {
					visit[y][x]=true;
					q.add(new int[] {y,x});
					tr.add(new int[] {y,x});
					int value=arr[y][x];
					while(!q.isEmpty()) {
						int[] p= q.poll();
						for(int i=0;i<4;i++) {
							int ny=p[0]+dy[i];
							int nx=p[1]+dx[i];
							if(nx==-1) nx=m-1;
							if(nx==m) nx=0; //경계 (원판은 out of bound 없음)
							if(ny>=1&&ny<=n&&value==arr[ny][nx]&&!visit[ny][nx]) {
								visit[ny][nx]=true;
								q.add(new int[] {ny,nx});
								tr.add(new int[] {ny,nx});
							}
						}
					}
					if(tr.size()>=2) {
						while(!tr.isEmpty()) list.add(tr.poll());
					}
					tr.clear();
				}
			}
		}
		return list;
	}
}