import java.io.*;
import java.util.*;

public class Main {
    static int n,k,answer=0;
    static int[][] map,horse,size;
    static int[][][] info;
    static int dy[] = {0,0,-1,1};
    static int dx[] = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());

        map=new int[n][n];
        for(int y=0;y<n;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<n;x++){
                map[y][x]=Integer.parseInt(st.nextToken());
            }
        }

        horse=new int[k][3]; //말 순차적으로 관리하기 위한 정보
        info=new int[n][n][k]; //맵에 존재하는 말의 정보
        size=new int[n][n]; //맵 각 위치에 놓인 말의 개수 정보
        for(int i=0;i<k;i++){
            st=new StringTokenizer(br.readLine());
            int y=Integer.parseInt(st.nextToken())-1;
            int x=Integer.parseInt(st.nextToken())-1;
            int d=Integer.parseInt(st.nextToken())-1;

            horse[i][0]=y;
            horse[i][1]=x;
            horse[i][2]=d;
            info[y][x][size[y][x]++]=i;
        }

        System.out.println(simulation());
    }
    static int simulation(){
        while(answer<1000) {
            ++answer;
            for (int i = 0; i < k; i++) {
                int[] curHorse = horse[i];
                int y=curHorse[0];
                int x=curHorse[1];
                int d=curHorse[2];

                int ny=y+dy[d];
                int nx=x+dx[d];

                if(isRange(ny,nx)){
                    if(map[ny][nx]==0) whiteMove(i,y,x,ny,nx);
                    else if(map[ny][nx]==1) redMove(i,y,x,ny,nx);
                    else blueMove(i,y,x);
                }else blueMove(i,y,x);

                if(over4()) return answer;
            }
        }
        return -1;
    }
    static boolean isRange(int y,int x){
        return 0<=y&&y<n&&0<=x&&x<n;
    }

    static void whiteMove(int number,int y,int x,int ny,int nx){
        for(int idx=0;idx<size[y][x];idx++){
            if(info[y][x][idx]==number){
                for(int j=idx;j<size[y][x];j++){
                    int num=info[y][x][j];
                    info[ny][nx][size[ny][nx]++]=num;
                    horse[num][0]=ny;
                    horse[num][1]=nx;
                }
                size[y][x]=idx; //size 업데이트
            }
        }
    }
    static void redMove(int number,int y,int x,int ny,int nx){
        for(int idx=0;idx<size[y][x];idx++) {
            if (info[y][x][idx] == number) {
                for (int j = size[y][x] - 1; j >= idx; j--) {
                    int num = info[y][x][j];
                    info[ny][nx][size[ny][nx]++] = num;
                    horse[num][0] = ny;
                    horse[num][1] = nx;
                }
                size[y][x] = idx; //size 업데이트
            }
        }
    }
    static void blueMove(int number,int y,int x){
        int d=horse[number][2];
        if(d%2==0) d+=1;
        else d-=1;

        horse[number][2]=d; // 방향 업데이트

        int nny=y+dy[d];
        int nnx=x+dx[d];
        if(isRange(nny,nnx) && map[nny][nnx]!=2) {
            if(map[nny][nnx]==0) whiteMove(number,y,x,nny,nnx);
            else redMove(number,y,x,nny,nnx);
        }
    }
    static boolean over4(){
        for(int y=0;y<n;y++){
            for(int x=0;x<n;x++){
                if(size[y][x]>=4) return true;
            }
        }
        return false;
    }
}