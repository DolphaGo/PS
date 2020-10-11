import java.io.*;
import java.util.*;

public class Main {
    static int h,w,t;
    static int[][] map;

    static int dy[]={-1,1,0,0};
    static int dx[]={0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h=Integer.parseInt(st.nextToken());
        w=Integer.parseInt(st.nextToken());
        t=Integer.parseInt(st.nextToken());
        map=new int[h][w];
        int[] cleaner=new int[2];
        for(int y=0;y<h;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<w;x++){
                map[y][x]=Integer.parseInt(st.nextToken());
                if(map[y][x]==-1){
                    if(cleaner[0]==0) cleaner[0]=y;
                    else cleaner[1]=y;
                }
            }
        }

        for(int T=1;T<=t;T++){
            spread();
            clean(cleaner);
        }

        int answer=0;
        for(int y=0;y<h;y++){
            for(int x=0;x<w;x++){
                if(map[y][x]>0) answer+=map[y][x];
            }
        }
        System.out.println(answer);
    }
    static void spread(){
        int[][] add=new int[h][w];
        for(int y=0;y<h;y++){
            for(int x=0;x<w;x++){
                if(map[y][x]>0){
                    int cnt=0;
                    int prev=map[y][x];
                    for(int i=0;i<4;i++){
                        int ny=y+dy[i];
                        int nx=x+dx[i];
                        if(isRange(ny,nx)&&map[ny][nx]!=-1){
                            ++cnt;
                            add[ny][nx]+=prev/5;
                        }
                    }
                    map[y][x]-=(prev/5)*cnt;
                }
            }
        }
        for(int y=0;y<h;y++){
            for(int x=0;x<w;x++){
                map[y][x]+=add[y][x];
            }
        }
    }
    static void clean(int[] cleaner){
        counterClockwise(cleaner[0]);
        clockwise(cleaner[1]);
    }

    static void counterClockwise(int cy) {
        for(int y=cy-2;y>=0;y--) map[y+1][0]=map[y][0];
        for(int x=1;x<w;x++) map[0][x-1]=map[0][x];
        for(int y=1;y<=cy;y++) map[y-1][w-1]=map[y][w-1];
        for(int x=w-2;x>=1;x--) map[cy][x+1]=map[cy][x];
        map[cy][1]=0;
    }

    static void clockwise(int cy) {
        for(int y=cy+2;y<h;y++) map[y-1][0]=map[y][0];
        for(int x=1;x<w;x++) map[h-1][x-1]=map[h-1][x];
        for(int y=h-2;y>=cy;y--) map[y+1][w-1]=map[y][w-1];
        for(int x=w-2;x>=1;x--) map[cy][x+1]=map[cy][x];
        map[cy][1]=0;
    }
    static boolean isRange(int y,int x){
        return 0<=y&&y<h&&0<=x&&x<w;
    }
}