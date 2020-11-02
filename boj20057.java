import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static Stack<int[]> stack=new Stack<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n=Integer.parseInt(br.readLine());
        map=new int[n][n];
        for(int y=0;y<n;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<n;x++){
                map[y][x]=Integer.parseInt(st.nextToken());
            }
        }

        makeDir();
        stack.push(new int[]{n/2,n/2,2});

        System.out.println(simulation());
    }
    static int simulation(){
        int res=0;
        while(!stack.isEmpty()){
            int[] p= stack.pop();
            if(stack.isEmpty()) break;
            int y=p[0];
            int x=p[1];
            int d=p[2];

            int ny=y+dy[d];
            int nx=x+dx[d];
            int sand=map[ny][nx];

            int sand10=(int)(sand*0.1);
            int sand07=(int)(sand*0.07);
            int sand05=(int)(sand*0.05);
            int sand02=(int)(sand*0.02);
            int sand01=(int)(sand*0.01);

            if(d==0||d==1){
                for(int i=-1;i<=1;i+=2){
                    int ny07=ny;
                    int nx07=nx+i;
                    if(!isRange(ny07,nx07)) res+=sand07;
                    else map[ny07][nx07]+=sand07;

                    int ny10=ny07+dy[d];
                    int nx10=nx07+dx[d];
                    if(!isRange(ny10,nx10)) res+=sand10;
                    else map[ny10][nx10]+=sand10;

                    int ny01=ny07-dy[d];
                    int nx01=nx07-dx[d];
                    if(!isRange(ny01,nx01)) res+=sand01;
                    else map[ny01][nx01]+=sand01;

                    int ny02=ny;
                    int nx02=nx+2*i;
                    if(!isRange(ny02,nx02)) res+=sand02;
                    else map[ny02][nx02]+=sand02;
                }
            }else{
                for(int i=-1;i<=1;i+=2){
                    int ny07=ny+i;
                    int nx07=nx;
                    if(!isRange(ny07,nx07)) res+=sand07;
                    else map[ny07][nx07]+=sand07;

                    int ny10=ny07+dy[d];
                    int nx10=nx07+dx[d];
                    if(!isRange(ny10,nx10)) res+=sand10;
                    else map[ny10][nx10]+=sand10;

                    int ny01=ny07-dy[d];
                    int nx01=nx07-dx[d];
                    if(!isRange(ny01,nx01)) res+=sand01;
                    else map[ny01][nx01]+=sand01;

                    int ny02=ny+2*i;
                    int nx02=nx;
                    if(!isRange(ny02,nx02)) res+=sand02;
                    else map[ny02][nx02]+=sand02;
                }
            }
            //5% 처리
            int ny05=y+3*dy[d];
            int nx05=x+3*dx[d];
            if(!isRange(ny05,nx05)) res+=sand05;
            else map[ny05][nx05]+=sand05;

            //a 지점
            int ay=ny+dy[d];
            int ax=nx+dx[d];
            int asand=sand-(sand05+2*(sand01+sand02+sand07+sand10));
            if(!isRange(ay,ax)) res+=asand;
            else map[ay][ax]+=asand;
        }
        return res;
    }
    static void makeDir(){
        boolean[][] visit=new boolean[n][n];
        int y=0,x=0;
        while(!(y==n/2&&x==n/2)){
            while(x<n&&!visit[y][x]) {
                visit[y][x]=true;
                stack.push(new int[]{y,x++,2});
            }
            x--; y++;
            while(y<n&&!visit[y][x]) {
                visit[y][x]=true;
                stack.push(new int[]{y++,x,0});
            }
            x--; y--;
            while(x>=0&&!visit[y][x]) {
                visit[y][x]=true;
                stack.push(new int[]{y,x--,3});
            }
            x++; y--;
            while(y>=0&&!visit[y][x]){
                visit[y][x]=true;
                stack.push(new int[]{y--,x,1});
            }
            y++; x++;
        }
    }
    static boolean isRange(int y,int x){
        return 0<=y&&y<n&&0<=x&&x<n;
    }
}