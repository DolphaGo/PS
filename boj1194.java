import java.io.*;
import java.util.*;

public class Main {
    static int h,w;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h=Integer.parseInt(st.nextToken());
        w=Integer.parseInt(st.nextToken());
        map=new char[h][w];
        Queue<int[]> q=new LinkedList<>();
        for(int y=0;y<h;y++){
            map[y]=br.readLine().toCharArray();
            for(int x=0;x<w;x++){
                if(map[y][x]=='0'){
                    q.add(new int[]{y,x,0,0});
                    map[y][x]='.';
                }
            }
        }
        boolean[][][] visit=new boolean[1<<6][h][w];
        int[] dy={-1,1,0,0};
        int[] dx={0,0,-1,1};
        while(!q.isEmpty()){
            int[] p=q.poll();
            int y=p[0];
            int x=p[1];
            int key=p[2];
            int cnt=p[3];
            for(int i=0;i<4;i++){
                int ny=y+dy[i];
                int nx=x+dx[i];
                if(isRange(ny,nx)&&map[ny][nx]!='#'&&!visit[key][ny][nx]){
                    char c=map[ny][nx];
                    if('a'<=c&&c<='f'){
                        int idx=c-'a';
                        int newKey=key | (1<<idx); //비트 켜기
                        visit[newKey][ny][nx]=true;
                        q.add(new int[]{ny,nx,newKey,cnt+1});
                    }else if('A'<=c && c<='F'){
                        int idx=c-'A';
                        if((key&(1<<idx))>0){
                            visit[key][ny][nx]=true;
                            q.add(new int[]{ny,nx,key,cnt+1});
                        }
                    }else if(c=='.'){
                        visit[key][ny][nx]=true;
                        q.add(new int[]{ny,nx,key,cnt+1});
                    }else{//출구
                        System.out.println(cnt+1);
                        return;
                    }
                }
            }
        }
        System.out.println(-1);
    }
    static boolean isRange(int y,int x){
        return 0<=y&&y<h&&0<=x&&x<w;
    }
}