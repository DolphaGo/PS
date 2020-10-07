import java.io.*;
import java.util.*;

public class Main {
    static int h,w;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static PriorityQueue<int[]> bridges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        h=Integer.parseInt(st.nextToken());
        w=Integer.parseInt(st.nextToken());

        int[][] map=new int[h][w];
        for(int y=0;y<h;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<w;x++){
                map[y][x]=Integer.parseInt(st.nextToken());
            }
        }

        int[][] renderMap=new int[h][w];
        int count=render(map,renderMap);
        bridges =new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2],o2[2]);
            }
        });

        findBridges(renderMap);
        int[] p=new int[count+1];
        for(int i=1;i<=count;i++) p[i]=i;

        int answer=0;
        int conn=0;
        while(conn<count-1 && !bridges.isEmpty()){
            int[] poll= bridges.poll();
            int a=poll[0];
            int b=poll[1];

            a=find(p,a);
            b=find(p,b);

            if(a!=b){
                ++conn;
                answer+=poll[2];
                if(a<b) p[b]=a;
                else p[a]=b;
            }
        }

        System.out.println(conn<count-1?-1:answer);
    }
    static int render(int[][] map,int[][] renderMap){
        Queue<int[]> q=new LinkedList<>();
        boolean[][] visit=new boolean[h][w];
        int count=0;
        for(int y=0;y<h;y++){
            for(int x=0;x<w;x++){
                if(map[y][x]==1 && !visit[y][x]){
                    visit[y][x]=true;
                    renderMap[y][x]= ++count;
                    q.add(new int[]{y,x});
                    while(!q.isEmpty()){
                        int[] p=q.poll();
                        for(int i=0;i<4;i++){
                            int ny=p[0]+dy[i];
                            int nx=p[1]+dx[i];
                            if(isRange(ny,nx)&&map[ny][nx]==1&&!visit[ny][nx]){
                                visit[ny][nx]=true;
                                renderMap[ny][nx]=count;
                                q.add(new int[]{ny,nx});
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
    static void findBridges(int[][] renderMap){
        for(int y=0;y<h;y++){
            for(int x=0;x<w;x++){
                if(renderMap[y][x]!=0){
                    int cur=renderMap[y][x];
                    //시작점으로부터 상,하,좌,우 다리 건설(거리 2이상)
                    for(int i=0;i<4;i++){
                        int ny=y+dy[i];
                        int nx=x+dx[i];
                        int len=0;
                        while(isRange(ny,nx)&&renderMap[ny][nx]==0){
                            ++len;
                            ny+=dy[i];
                            nx+=dx[i];
                        }

                        if(isRange(ny,nx) && renderMap[ny][nx]!=cur && len>=2){
                            bridges.add(new int[]{cur,renderMap[ny][nx],len});
                        }
                    }
                }
            }
        }
    }
    static boolean isRange(int y,int x){
        return 0<=y&&y<h&&0<=x&&x<w;
    }
    static int find(int[] p,int a){
        if(p[a]==a) return a;
        else return p[a]=find(p,p[a]);
    }
}