import java.io.*;
import java.util.*;

public class Main {
    static int h,w;
    static char[][] map;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        h=Integer.parseInt(st.nextToken());
        w=Integer.parseInt(st.nextToken());
        map=new char[h][w];

        for(int y=0;y<h;y++){
            map[y]=br.readLine().toCharArray();
        }

        go();
        print();
    }
    static void go(){
        Queue<int[]> q=new LinkedList<>();

        for(int y=0;y<h;y++){
            for(int x=0;x<w;x++){
                if(map[y][x]=='X'){
                    int cnt=0;
                    for(int i=0;i<4;i++){
                        int ny=y+dy[i];
                        int nx=x+dx[i];
                        if(!isRange(ny,nx)) ++cnt;
                        else if(map[ny][nx]=='.') ++cnt;
                    }
                    if(cnt>=3) q.add(new int[]{y,x});
                }
            }
        }

        while(!q.isEmpty()){
            int[] p=q.poll();
            map[p[0]][p[1]]='.';
        }
    }
    static void print(){
        StringBuilder sb=new StringBuilder();
        int sy=h-1,sx=w-1,ey=0,ex=0;
        for(int y=0;y<h;y++){
            for(int x=0;x<w;x++){
                if(map[y][x]=='X'){
                    sy=Math.min(sy,y);
                    sx=Math.min(sx,x);
                    ey=Math.max(ey,y);
                    ex=Math.max(ex,x);
                }
            }
        }
        for(int y=sy;y<=ey;y++){
            for(int x=sx;x<=ex;x++){
                sb.append(map[y][x]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    static boolean isRange(int y,int x){
        return 0<=y&&y<h&&0<=x&&x<w;
    }
}