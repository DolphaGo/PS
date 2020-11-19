import java.io.*;
import java.util.*;

public class Main {
    static int dy[]={-1,1,0,0,-1,1,-1,1};
    static int dx[]={0,0,-1,1,-1,1,1,-1};
    static int h,w;
    static int[][] map;
    static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb=new StringBuilder();
        while(true){
            st=new StringTokenizer(br.readLine());
            w=Integer.parseInt(st.nextToken());
            h=Integer.parseInt(st.nextToken());
            if(w==0&&h==0) break;

            map=new int[h][w];
            visit=new boolean[h][w];
            for(int y=0;y<h;y++){
                st=new StringTokenizer(br.readLine());
                for(int x=0;x<w;x++){
                    map[y][x]=Integer.parseInt(st.nextToken());
                }
            }
            int answer=0;
            for(int y=0;y<h;y++){
                for(int x=0;x<w;x++){
                    if(map[y][x]==1&&!visit[y][x]){
                        ++answer;
                        visit[y][x]=true;
                        dfs(y,x);
                    }
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
    static void dfs(int y,int x){
        for(int i=0;i<8;i++){
            int ny=y+dy[i];
            int nx=x+dx[i];
            if(isRange(ny,nx)&&map[ny][nx]==1&&!visit[ny][nx]){
                visit[ny][nx]=true;
                dfs(ny,nx);
            }
        }
    }
    static boolean isRange(int y,int x){
        return 0<=x&&x<w&&0<=y&&y<h;
    }
}