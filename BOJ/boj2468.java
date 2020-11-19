import java.util.*;
import java.io.*;

public class Main {
    static int dy[]={-1,1,0,0};
    static int dx[]={0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        int[][] map=new int[n][n];
        int max=0;
        for(int y=0;y<n;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<n;x++){
                map[y][x]=Integer.parseInt(st.nextToken());
                max=max<map[y][x]?map[y][x]:max;
            }
        }
        Queue<int[]> q=new LinkedList<>();
        int answer=0;
        for(int h=0;h<max;h++){
            int cnt=0;
            boolean[][] visit=new boolean[n][n];
            for(int y=0;y<n;y++){
                for(int x=0;x<n;x++){
                    if(map[y][x]>h&&!visit[y][x]){
                        ++cnt;
                        visit[y][x]=true;
                        q.offer(new int[]{y,x});
                        while(!q.isEmpty()){
                            int[] p=q.poll();
                            for(int i=0;i<4;i++){
                                int ny=p[0]+dy[i];
                                int nx=p[1]+dx[i];
                                if(ny>=0&&nx>=0&&ny<n&&nx<n&&map[ny][nx]>h&&!visit[ny][nx]){
                                    visit[ny][nx]=true;
                                    q.offer(new int[]{ny,nx});
                                }
                            }
                        }
                    }
                }
            }
            answer=answer<cnt?cnt:answer;
        }
        System.out.println(answer);
    }
}
