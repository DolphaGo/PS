import java.io.*;
import java.util.*;

public class Main {
    static int h,w,answer;
    //순서대로 시계방향
    static int[] dy={-1,0,1,0};
    static int[] dx={0,1,0,-1};

    static int[][][] dir={
            {},
            {{0},{1},{2},{3}}, //1번 카메라
            {{0,2},{1,3}}, // 2번 카메라
            {{0,1},{1,2},{2,3},{3,0}}, //3번 카메라
            {{0,1,2},{1,2,3},{2,3,0},{3,0,1}},//4번 카메라
            {{0,1,2,3}} //5번 카메라
    };

    static ArrayList<int[]> cctv=new ArrayList<>();
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
                if(map[y][x]>0&&map[y][x]!=6) cctv.add(new int[]{y,x,map[y][x]});
            }
        }

        answer=Integer.MAX_VALUE;
        simulation(map,0);
        System.out.println(answer);
    }
    static void simulation(int[][] map,int v){
        if(v==cctv.size()){
            int res=get(map);
            answer=Math.min(answer,res);
            return;
        }

        int[][] dup=new int[h][w];
        int[] cur=cctv.get(v);
        for(int i=0;i<dir[cur[2]].length;i++){
            for(int y=0;y<h;y++){
                for(int x=0;x<w;x++){
                    dup[y][x]=map[y][x];
                }
            }

            for(int j=0;j<dir[cur[2]][i].length;j++){
                int d=dir[cur[2]][i][j];
                int ny=cur[0]+dy[d];
                int nx=cur[1]+dx[d];
                while(isRange(ny,nx)&&dup[ny][nx]!=6){
                    dup[ny][nx] = -1;
                    ny += dy[d];
                    nx += dx[d];
                }
            }
            simulation(dup,v+1);
        }
    }
    static int get(int[][] map){
        int res=0;
        for(int y=0;y<h;y++){
            for(int x=0;x<w;x++){
                if(map[y][x]==0) ++res;
            }
        }
        return res;
    }

    static boolean isRange(int y,int x){
        return 0<=y&&y<h&&0<=x&&x<w;
    }
}