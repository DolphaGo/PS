import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class 호석배2번 {
    static int h,w,r;
    static int[][] map;
    static char[][] state;
    static int[] dy={0,0,1,-1};
    static int[] dx={1,-1,0,0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        h=Integer.parseInt(st.nextToken());
        w=Integer.parseInt(st.nextToken());
        r=Integer.parseInt(st.nextToken());

        map=new int[h+1][w+1];
        state =new char[h+1][w+1];
        for(int y=1;y<=h;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=1;x<=w;x++){
                map[y][x]=Integer.parseInt(st.nextToken());
                state[y][x] = 'S';
            }
        }

        int score=0;
        for(int R=0;R<r;R++){
            st=new StringTokenizer(br.readLine()); //공격수
            int r=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            char d=st.nextToken().charAt(0);
            score+=attack(r,c,d);

            st=new StringTokenizer(br.readLine()); //수비수
            int y=Integer.parseInt(st.nextToken());
            int x=Integer.parseInt(st.nextToken());
            //(y,x)를 세운다.
            defence(y,x);
        }
        System.out.println(score);
        StringBuilder sb=new StringBuilder();
        for(int y=1;y<=h;y++){
            for(int x=1;x<=w;x++){
                sb.append(state[y][x]+" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void defence(int y, int x) {
        state[y][x]='S';
    }

    static int attack(int r,int c,char dir){
        int score=0;
        int d= cvtDirtoInt(dir);
        if(state[r][c]=='F') return 0;
        Queue<int[]> q=new LinkedList<>();
        q.add(new int[]{r,c}); //넘어뜨리는 시작점
        while(!q.isEmpty()){
            int[] p=q.poll();
            int y=p[0];
            int x=p[1];
            int len=map[y][x];
            int ny=y+(len-1)*dy[d];
            int nx=x+(len-1)*dx[d];

            int startY=ny<y?ny:y;
            int endY=ny<y?y:ny;
            int startX=nx<x?nx:x;
            int endX=nx<x?x:nx;
            //현재 지점부터 넘어뜨릴 수 있는 구간 도미노 탐색
            for(int ty=startY;ty<=endY;ty++){
                for(int tx=startX;tx<=endX;tx++){
                    if(isRange(ty,tx)&&state[ty][tx]=='S'){
                        ++score;
                        state[ty][tx]='F';
                        q.add(new int[]{ty,tx});
                    }
                }
            }
        }
        return score;
    }

    private static boolean isRange(int y, int x) {
        return 1<=y&&y<=h&&1<=x&&x<=w;
    }

    static int cvtDirtoInt(char dir){
        if(dir=='E') return 0;
        else if(dir=='W') return 1;
        else if(dir=='S') return 2;
        else return 3;
    }
}