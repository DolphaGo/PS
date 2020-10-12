import java.util.*;
import java.io.*;

public class Main {
    static class Info{
        int y;
        int x;
        int dir;
        Info(){};
        public void setYX(int y,int x){
            this.y=y;
            this.x=x;
        }
        public void setDir(int dir){
            this.dir=dir;
        }
        public String toString(){
            return y+","+x+","+dir;
        }
    }
    static int n,m,k,answer;
    static int[][] map,time;
    static int[][][] priority;
    static Info[] infos;
    static int[] dy={0,-1,1,0,0};
    static int[] dx={0,0,0,-1,1};
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());

        map=new int[n][n];
        time=new int[n][n];
        infos=new Info[m+1];
        for(int i=1;i<=m;i++) infos[i]=new Info();

        for(int y=0;y<n;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<n;x++){
                map[y][x]=Integer.parseInt(st.nextToken());
                if(map[y][x]>0) {
                    infos[map[y][x]].setYX(y,x);
                    time[y][x]=k;
                }
            }
        }

        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=m;i++) {
            int dir=Integer.parseInt(st.nextToken());
            infos[i].setDir(dir);
        }

        priority=new int[m+1][5][5];
        for(int i=1;i<=m;i++){
            for(int j=1;j<=4;j++){
                st=new StringTokenizer(br.readLine());
                for(int k=1;k<=4;k++){
                    priority[i][j][k]=Integer.parseInt(st.nextToken());
                }
            }
        }

        answer=-1;
        simulation();
        System.out.println(answer);
    }
    static void simulation(){
        int t=0;
        ArrayList<Integer> blank=new ArrayList<>();
        ArrayList<Integer> my=new ArrayList<>();
        Queue<int[]> buffer=new LinkedList<>();
        while(t<1000) {
            t++;

            for (int num = 1; num <= m; num++) {
                Info cur=infos[num];
                int y=cur.y;
                int x=cur.x;
                int d=cur.dir;
                if(y==-1&&x==-1) continue; // 장외처리된 상어는 이동 처리하지 않음

                for(int i=1;i<=4;i++){
                    int ny=y+dy[i];
                    int nx=x+dx[i];
                    if(isRange(ny,nx)){
                        if(map[ny][nx]==0){
                            //1. 아무 냄새가 없는 칸
                            blank.add(i);
                        }else if(map[ny][nx]==map[y][x]){
                            //2. 없으면 자신의 냄새가 있는 칸
                            my.add(i);
                        }
                    }
                }
                //2-1. 여러개면 특정 우선순위를 따른다.
                //현재 방향에 따라 다음을 선택
                if(!blank.isEmpty()){
                    int[] p=priority[num][d];
                    for(int i=1;i<=4;i++){
                        int now=p[i];
                        if(blank.contains(now)){
                            int ny=y+dy[now];
                            int nx=x+dx[now];
                            buffer.add(new int[]{num,ny,nx,now});
                            break;
                        }
                    }
                }else{
                    int[] p=priority[num][d];
                    for(int i=1;i<=4;i++){
                        int now=p[i];
                        if(my.contains(now)){
                            int ny=y+dy[now];
                            int nx=x+dx[now];
                            buffer.add(new int[]{num,ny,nx,now});
                            break;
                        }
                    }
                }
                blank.clear();
                my.clear();
            }
            while(!buffer.isEmpty()){
                int[] p=buffer.poll();
                int num=p[0];
                int ny=p[1];
                int nx=p[2];
                int ndir=p[3];
                if(map[ny][nx]==0){ //빈 공간이었다면 그대로 insert
                    map[ny][nx]=num;
                    time[ny][nx]=k+1;
                    infos[num].setYX(ny,nx);
                    infos[num].setDir(ndir);
                }else if(map[ny][nx]==num){ // 자신의 자취로 돌아간다면
                    time[ny][nx]=k+1; //해당 위치 시간 갱신
                    infos[num].setYX(ny,nx);
                    infos[num].setDir(ndir);
                }else{//다른 상어가 미리 왔다면(낮은 번호부터 진행하기 때문)
                    infos[num].setYX(-1,-1); // 장외
                }
            }
            for(int y=0;y<n;y++){
                for(int x=0;x<n;x++){
                    if(time[y][x]>0) {
                        if(--time[y][x]==0) map[y][x]=0;
                    }
                }
            }
            int cnt=0;
            for(int i=1;i<=m;i++){
                if(infos[i].y!=-1) ++cnt;
            }

            if(cnt==1) {
                answer=t;
                break;
            }
        }
    }
    static boolean isRange(int y,int x){
        return 0<=y&&y<n&&0<=x&&x<n;
    }
}