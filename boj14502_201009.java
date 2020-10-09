import java.io.*;
import java.util.*;

public class Main {
    static int h,w,answer,zero;
    static List<int[]> virus,vacancy;
    static int[][] map;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        h=Integer.parseInt(st.nextToken());
        w=Integer.parseInt(st.nextToken());
        map=new int[h][w];
        virus=new ArrayList<>();
        vacancy=new ArrayList<>();
        for(int y=0;y<h;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<w;x++){
                map[y][x]=Integer.parseInt(st.nextToken());
                if(map[y][x]==2) virus.add(new int[]{y,x});
                if(map[y][x]==0) vacancy.add(new int[]{y,x});
            }
        }
        answer=0;
        zero=vacancy.size();
        select(0,0);
        System.out.println(answer);
    }

    static void select(int v,int select){
        if(select==3){
            int res=simulation();
            answer=Math.max(answer,res);
            return;
        }
        if(v==vacancy.size()) return;

        int[] cur=vacancy.get(v);
        map[cur[0]][cur[1]]=1;
        select(v+1,select+1);
        map[cur[0]][cur[1]]=0;
        select(v+1,select);
    }
    static int simulation(){
        Queue<int[]> q=new LinkedList<>();
        boolean[][] visit=new boolean[h][w];
        for(int[] v:virus){
            visit[v[0]][v[1]]=true;
            q.add(v);
        }
        int res=zero-3;
        while(!q.isEmpty()){
            int[] p=q.poll();
            for(int i=0;i<4;i++){
                int ny=p[0]+dy[i];
                int nx=p[1]+dx[i];
                if(isRange(ny,nx)&&map[ny][nx]==0&&!visit[ny][nx]){
                    visit[ny][nx]=true;
                    q.add(new int[]{ny,nx});
                    --res;
                }
            }
        }
        return res;
    }
    static boolean isRange(int y,int x){
        return 0<=y&&y<h&&0<=x&&x<w;
    }
}