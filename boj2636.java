import java.io.*;
import java.util.*;

public class Main {
    static int h,w;
    static int dy[]={-1,1,0,0};
    static int dx[]={0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        
        h=Integer.parseInt(st.nextToken());
        w=Integer.parseInt(st.nextToken());

        int[][] map=new int[h][w];
        int one=0;

        for(int y=0;y<h;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<w;x++){
                map[y][x]=Integer.parseInt(st.nextToken());
                if(map[y][x]==1) ++one;
            }
        }
        int time=0;
        int answer=0;

        Queue<int[]> q=new LinkedList<>();
        Queue<int[]> d=new LinkedList<>();
        boolean[][] visit=new boolean[h][w];

        q.add(new int[]{0,0});
        visit[0][0]=true;
        go(map,visit,q,d);

        while(one>0){
            ++time;
            if(one==d.size()) answer=d.size(); //공기에 맞닿아 있는 치즈 수
            while(!d.isEmpty()) {
                q.add(d.poll());
                --one;
            }
            if(one>0) go(map,visit,q,d);
        }

        System.out.println(time+"\n"+answer);
    }
    static void go(int[][] map, boolean[][] visit, Queue<int[]> q, Queue<int[]> d){

        while(!q.isEmpty()){
            int[] p=q.poll();
            for(int i=0;i<4;i++){
                int ny=p[0]+dy[i];
                int nx=p[1]+dx[i];
                if(isRange(ny,nx)&&!visit[ny][nx]){
                    visit[ny][nx] = true;

                    if(map[ny][nx]==0) q.add(new int[]{ny, nx});
                    else d.add(new int[]{ny,nx});
                }
            }
        }
    }
    static boolean isRange(int y,int x){
        return 0<=x&&x<w&&0<=y&&y<h;
    }
}
