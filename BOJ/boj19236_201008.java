import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    static int dy[]={0,-1,-1,0,1,1,1,0,-1};
    static int dx[]={0,0,-1,-1,-1,0,1,1,1};
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] num=new int[4][4];
        int[][] dir=new int[4][4];
        for(int y=0;y<4;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<4;x++){
                num[y][x]=Integer.parseInt(st.nextToken());
                dir[y][x]=Integer.parseInt(st.nextToken());
            }
        }
        int score=num[0][0];
        int d=dir[0][0];
        num[0][0]=dir[0][0]=0;
        dfs(num,dir,0,0,score,d);
        System.out.println(answer);
    }
    static void dfs(int[][] num,int[][] dir,int sy,int sx,int score,int d){
        answer=Math.max(answer,score);
        int[][][] res=fishMove(num,dir,sy,sx);

        int[][] newNum=res[0];
        int[][] newDir=res[1];

        for(int jump=1;jump<=3;jump++){
            int ny=sy+jump*dy[d];
            int nx=sx+jump*dx[d];
            if(isRange(ny,nx)&&newNum[ny][nx]!=0){
                int temp=newNum[ny][nx];
                int temp_dir=newDir[ny][nx];

                newNum[ny][nx]=0;
                newDir[ny][nx]=0;
                dfs(newNum,newDir,ny,nx,score+temp,temp_dir);
                newNum[ny][nx]=temp;
                newDir[ny][nx]=temp_dir;
            }
        }
    }
    static int[][][] fishMove(int[][] num,int[][] dir,int sy,int sx){
        int[][][] res=new int[2][4][4];
        int[][] newNum=new int[4][4];
        int[][] newDir=new int[4][4];
        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                newNum[y][x]=num[y][x];
                newDir[y][x]=dir[y][x];
            }
        }

        for(int number=1;number<=16;number++){
            loop:for(int y=0;y<4;y++){
                for(int x=0;x<4;x++){
                    if(newNum[y][x]==number){
                        int d=newDir[y][x];
                        //범위를 벗어나거나 다음 위치가 상어일 때까지
                        int ny=0;
                        int nx=0;
                        int ndir=0;
                        //현재 방향부터 갈 수 있는지 검사
                        boolean flag=false;
                        for(int i=0;i<=7;i++){
                            ndir=(d+i)%8;
                            if(ndir==0) ndir=8;

                            ny=y+dy[ndir];
                            nx=x+dx[ndir];
                            //갈 수 있다면
                            if(isRange(ny,nx)&&!(ny==sy&&nx==sx)){
                                flag=true;
                                newDir[y][x]=ndir;
                                break;
                            }
                        }

                        //이동 가능하다면
                        if(flag){
                            //물고기 교체
                            int temp=newNum[y][x];
                            newNum[y][x]=newNum[ny][nx];
                            newNum[ny][nx]=temp;
                            
                            //방향 교체
                            int temp_dir=newDir[y][x];
                            newDir[y][x]=newDir[ny][nx];
                            newDir[ny][nx]=temp_dir;
                        }
                        break loop;
                    }
                }
            }
        }

        res[0]=newNum;
        res[1]=newDir;
        return res;
    }
    static boolean isRange(int y,int x){
        return 0<=y&&y<4&&0<=x&&x<4;
    }
}