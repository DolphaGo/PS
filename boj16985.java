import java.io.*;
import java.util.*;

public class Main {
    static int[][][] map;
    static int answer;
    static int[] rotateInfo,turn;
    static boolean[] check;
    static int[] dz={-1,1,0,0,0,0};
    static int[] dy={0,0,-1,1,0,0};
    static int[] dx={0,0,0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map=new int[5][5][5];
        for(int z=0;z<5;z++){
            for(int y=0;y<5;y++){
                st=new StringTokenizer(br.readLine());
                for(int x=0;x<5;x++){
                    map[z][y][x]=Integer.parseInt(st.nextToken());
                }
            }
        }
        rotateInfo=new int[5];
        turn=new int[5];
        check=new boolean[5];
        answer=Integer.MAX_VALUE;
        go(0);
        System.out.println(answer==Integer.MAX_VALUE?-1:answer);
    }
    static void go(int v){
        if(v==5){
            int[][][] rotated=getRotated();
            makeTurn(0,rotated);
            return;
        }
        for(int i=0;i<4;i++){
            rotateInfo[v]=i;
            go(v+1);
        }
    }
    static void makeTurn(int v,int[][][] rotated){
        if(v==5){
            int[][][] simulationMap=new int[5][5][5];
            for(int i=0;i<5;i++){
                int idx=turn[i];
                for(int y=0;y<5;y++){
                    for(int x=0;x<5;x++){
                        simulationMap[idx][y][x]=rotated[i][y][x];
                    }
                }
            }
            int res=simulation(simulationMap);
            answer=Math.min(answer,res);
            return;
        }

        for(int i=0;i<5;i++){
            if(!check[i]){
                turn[v]=i;
                check[i]=true;
                makeTurn(v+1,rotated);
                check[i]=false;
            }
        }
    }

    private static int simulation(int[][][] rotated) {
        if(rotated[0][0][0]==0) return Integer.MAX_VALUE;

        Queue<int[]> q=new LinkedList<>();
        boolean[][][] visit=new boolean[5][5][5];
        visit[0][0][0]=true;
        q.add(new int[]{0,0,0,0});
        while(!q.isEmpty()){
            int[] p=q.poll();
            int z=p[0];
            int y=p[1];
            int x=p[2];
            int move=p[3];
            if(z==4&&y==4&&x==4) return move;
            for(int i=0;i<6;i++){
                int nz=z+dz[i];
                int ny=y+dy[i];
                int nx=x+dx[i];
                if(isRange(nz,ny,nx)&&rotated[nz][ny][nx]==1&&!visit[nz][ny][nx]){
                    visit[nz][ny][nx]=true;
                    q.add(new int[]{nz,ny,nx,move+1});
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    static int[][][] getRotated(){
        int[][][] res=new int[5][5][5];
        for(int i=0;i<5;i++){
            int state=rotateInfo[i];
            int[][] result=rotate(i,state);
            //깊은 복사
            for(int y=0;y<5;y++){
                for(int x=0;x<5;x++){
                    res[i][y][x]=result[y][x];
                }
            }
        }
        return res;
    }
    static int[][] rotate(int idx,int state){
        switch (state){
            case 0:
                return map[idx];
            case 1:
                return clockwise(map[idx]);
            case 2:
                return clockwise(clockwise(map[idx]));
            case 3:
                return counterClockwise(map[idx]);
        }
        return new int[5][5];
    }
    static int[][] clockwise(int[][] map){
        int[][] newMap=new int[5][5];
        for(int y=0;y<5;y++){
            for(int x=0;x<5;x++){
                newMap[x][4-y]=map[y][x];
            }
        }
        return newMap;
    }
    static int[][] counterClockwise(int[][] map){
        int[][] newMap=new int[5][5];
        for(int y=0;y<5;y++){
            for(int x=0;x<5;x++){
                newMap[4-x][y]=map[y][x];
            }
        }
        return newMap;
    }
    static boolean isRange(int z,int y,int x){
        return 0<=z&&z<5&&0<=y&&y<5&&0<=x&&x<5;
    }
}