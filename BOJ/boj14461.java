import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int t=Integer.parseInt(st.nextToken());

        int[][] map=new int[n][n];
        for(int y=0;y<n;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<n;x++){
                map[y][x]=Integer.parseInt(st.nextToken());
            }
        }

        int[] dy={-1,1,0,0};
        int[] dx={0,0,-1,1};

        PriorityQueue<int[]> q=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[3],o2[3]);
            }
        });
        boolean[][][] visit=new boolean[n][n][3];
        // int[] : (y,x,move,cost)
        visit[0][0][0]=true;
        q.add(new int[]{0,0,0,0});
        while(!q.isEmpty()){
            int[] p=q.poll();
            int y=p[0];
            int x=p[1];
            int move=p[2];
            int cost=p[3];

            if(y==n-1&&x==n-1){
                System.out.println(cost);
                return;
            }

            for(int i=0;i<4;i++){
                int ny=y+dy[i];
                int nx=x+dx[i];
                if(ny>=0&&nx>=0&&ny<n&&nx<n&&!visit[ny][nx][move]){
                    visit[ny][nx][move]=true;
                    if(move==2) q.add(new int[]{ny,nx,0,cost+map[ny][nx]+t});
                    else q.add(new int[]{ny,nx,move+1,cost+t});
                }
            }
        }
    }
}