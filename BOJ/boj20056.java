import java.io.*;
import java.util.*;

public class Main {
    static int n,m,k;
    static int[] dy={-1,-1,0,1,1,1,0,-1};
    static int[] dx={0,1,1,1,0,-1,-1,-1};
    static class FireBall{
        int y, x, m, s, d;
        public FireBall(int y, int x, int m, int s, int d){
            this.y = y; this.x = x; this.m=m; this.s=s; this.d=d;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());

        Queue<FireBall> fireBalls=new LinkedList<>();
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int r=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            int s=Integer.parseInt(st.nextToken());
            int d=Integer.parseInt(st.nextToken());
            fireBalls.add(new FireBall(r,c,m,s,d));
        }

        while(k-->0) simulation(fireBalls);

        int answer=0;
        while(!fireBalls.isEmpty()){
            answer+=fireBalls.poll().m;
        }
        System.out.println(answer);
    }
    private static void simulation(Queue<FireBall> fireBalls){
        List<FireBall>[][] map=new ArrayList[n+1][n+1];
        while(!fireBalls.isEmpty()){
            FireBall fireBall=fireBalls.poll();
            int ny=(fireBall.y +fireBall.s*dy[fireBall.d])%n;
            int nx=(fireBall.x +fireBall.s*dx[fireBall.d])%n;

            if(ny<=0) ny+=n;
            if(nx<=0) nx+=n;

            if(map[ny][nx]==null){
                map[ny][nx]=new ArrayList<>();
            }
            map[ny][nx].add(new FireBall(ny,nx, fireBall.m,fireBall.s,fireBall.d));
        }

        for(int y=1;y<=n;y++){
            for(int x=1;x<=n;x++){
                if(map[y][x]!=null){
                    if(map[y][x].size()>=2){
                        int m=0,s=0;
                        int pivot=(map[y][x].get(0).d)%2;
                        boolean flag=true;
                        for(FireBall ball:map[y][x]){
                            m+=ball.m;
                            s+=ball.s;
                            if(ball.d%2 != pivot) flag=false;
                        }
                        m/=5;
                        if(m==0) continue; //소멸
                        s/=map[y][x].size();

                        if(flag) { //모두 홀수 or 모두 짝수
                            for (int d = 0; d <= 6; d += 2) {
                                fireBalls.add(new FireBall(y, x, m, s, d));
                            }
                        }else { // 그 외
                            for (int d = 1; d <= 7; d += 2) {
                                fireBalls.add(new FireBall(y, x, m, s, d));
                            }
                        }
                    }else fireBalls.add(map[y][x].get(0));
                }
            }
        }
    }
}