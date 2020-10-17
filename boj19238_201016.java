import java.io.*;
import java.util.*;

public class Main {
	static int n,ty,tx;
	static int[][] map;
	static HashMap<Integer,int[]> endPoint;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        
        n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int o=Integer.parseInt(st.nextToken());
        
        map=new int[n][n];
        for(int y=0;y<n;y++) {
        	st=new StringTokenizer(br.readLine());
        	for(int x=0;x<n;x++) {
        		map[y][x]=Integer.parseInt(st.nextToken());
        	}
        }
        
        st=new StringTokenizer(br.readLine());
        ty=Integer.parseInt(st.nextToken())-1;
        tx=Integer.parseInt(st.nextToken())-1;
        
        endPoint=new HashMap<>();
        for(int i=2;i<=m+1;i++) {
        	st=new StringTokenizer(br.readLine());
        	int sy=Integer.parseInt(st.nextToken())-1;
        	int sx=Integer.parseInt(st.nextToken())-1;
        	map[sy][sx]=i;
        	
        	int ey=Integer.parseInt(st.nextToken())-1;
        	int ex=Integer.parseInt(st.nextToken())-1;
        	endPoint.put(i,new int[] {ey,ex});
        }
        System.out.println(simulation(m,o));
    }
    static int simulation(int m,int oil) {
    	for(int i=0;i<m;i++) {
    		int[] findMember=find(oil);
    		if(findMember==null) return -1; //손님을 찾으러 갈 수 없을 때
    		int move=findMember[0];
    		ty=findMember[1];
    		tx=findMember[2];
    		
    		int num=map[ty][tx];
    		map[ty][tx]=0; //손님을 태웠음.
    		oil-=move; //기름 업데이트
    		int[] ep=endPoint.get(num);
    		int[] result=drive(ep[0],ep[1],oil);
    		if(result==null) return -1; //손님을 데려다 줄 수 없을 때
    		
    		ty=result[0];
    		tx=result[1];
    		oil=result[2];
    		endPoint.remove(num);
    	}
    	return oil;
    }
    static int[] dy= {-1,1,0,0};
    static int[] dx= {0,0,-1,1};
    static int[] find(int oil) {
    	PriorityQueue<int[]> fq=new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]==o2[0]&&o1[1]==o2[1]) {
					return Integer.compare(o1[2], o2[2]);
				}else if(o1[0]==o2[0]) {
					return Integer.compare(o1[1], o2[1]);
				}else return Integer.compare(o1[0], o2[0]);
			}
		});
    	Queue<int[]> q=new LinkedList<>();
    	boolean[][] visit=new boolean[n][n];
    	visit[ty][tx]=true;
    	q.add(new int[] {ty,tx,oil,0});
    	while(!q.isEmpty()) {
    		int[] p=q.poll();
    		int curOil=p[2];
    		int move=p[3];
    		if(map[p[0]][p[1]]>1) {
    			fq.add(new int[] {move,p[0],p[1]});
    			continue;
    		}
    		if(curOil==0) continue;
    		for(int i=0;i<4;i++) {
    			int ny=p[0]+dy[i];
    			int nx=p[1]+dx[i];
    			if(isRange(ny,nx)&&map[ny][nx]!=1&&!visit[ny][nx]) {
    				visit[ny][nx]=true;
    				q.add(new int[] {ny,nx,curOil-1,move+1});
    			}
    		}
    	}
    	if(fq.size()>0) return fq.poll();
    	return null;
    }
    static boolean isRange(int y,int x) {
    	return 0<=y&&y<n&&0<=x&&x<n;
    }
    static int[] drive(int ey,int ex,int oil) {
    	Queue<int[]> q=new LinkedList<>();
    	boolean[][] visit=new boolean[n][n];
    	visit[ty][tx]=true;
    	q.add(new int[] {ty,tx,oil,0});
    	while(!q.isEmpty()) {
    		int[] p=q.poll();
    		int curOil=p[2];
    		int move=p[3];
    		if(p[0]==ey&&p[1]==ex) {
    			curOil+=2*move;
    			return new int[] {ey,ex,curOil};
    		}
    		if(curOil==0) continue;
    		for(int i=0;i<4;i++) {
    			int ny=p[0]+dy[i];
    			int nx=p[1]+dx[i];
    			if(isRange(ny,nx)&&map[ny][nx]!=1&&!visit[ny][nx]) {
    				visit[ny][nx]=true;
    				q.add(new int[] {ny,nx,curOil-1,move+1});
    			}
    		}
    	}
    	return null;
    }
}