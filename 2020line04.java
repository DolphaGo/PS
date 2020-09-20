import java.util.*;

public class Main4 {
    static int n,answer;
    static int dy[]={-1,0,1,0};
    static int dx[]={0,1,0,-1};
    public static void main(String[] args) {
        int[][] maze=new int[][]{
                {0, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 1, 1}, {0, 0, 0, 0, 0, 0}, {1, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0}, {1, 1, 0, 1, 1, 0}

        };

        n=maze.length;
        answer=0;
        go(maze,0,0,2,0);

        System.out.println(answer);
    }
    static void go(int[][] maze,int y,int x,int dir,int move){
        if(y==n-1&&x==n-1) {
            answer=move;
            return;
        }
        int cdir=dir-1;
        if(cdir==-1) cdir=3;

        //왼쪽에 벽이 있는지 확인한다.
        int cy=y+dy[cdir];
        int cx=x+dx[cdir];

        //현재 위치에서 왼쪽이 벽이라면 현재 보는 방향 직진
        if(!isRange(cy,cx)||maze[cy][cx]==1){
            int ny=y+dy[dir];
            int nx=x+dx[dir];

            //앞으로 전진할 수 있는 경우
            if(isRange(ny,nx)&&maze[ny][nx]==0){
                go(maze,ny,nx,dir,move+1);
            }else{//앞으로 전진할 수 없는 경우
                //오른쪽으로 방향 전환
                int ndir=dir+1;
                if(ndir==4) ndir=0;
                go(maze,y,x,ndir,move);
            }

        }else{//벽이 아니라면 왼쪽으로 방향을 틀고 한칸 전진한다.
            int ndir=dir-1;
            if(ndir==-1) ndir=3;
            int nny=y+dy[ndir];
            int nnx=x+dx[ndir];
            go(maze,nny,nnx,ndir,move+1);
        }
    }
    static boolean isRange(int y,int x){
        return 0<=x&&x<n&&0<=y&&y<n;
    }
}