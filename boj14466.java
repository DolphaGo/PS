import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[][] visit;
    static List<int[]> cows;
    static List<int[]>[][] map;
    static int dy[]={-1,1,0,0};
    static int dx[]={0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());
        int R=Integer.parseInt(st.nextToken());

        map=new ArrayList[N+1][N+1];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                map[i][j]=new ArrayList<>();
            }
        }
        for(int i=0;i<R;i++){
            st=new StringTokenizer(br.readLine());
            int r1=Integer.parseInt(st.nextToken());
            int c1=Integer.parseInt(st.nextToken());
            int r2=Integer.parseInt(st.nextToken());
            int c2=Integer.parseInt(st.nextToken());

            map[r1][c1].add(new int[]{r2,c2});
            map[r2][c2].add(new int[]{r1,c1});
        }

        cows=new ArrayList<>();
        for(int i=0;i<K;i++){
            st=new StringTokenizer(br.readLine());
            int r=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            cows.add(new int[]{r,c});
        }

        int answer=0;
        visit=new boolean[N+1][N+1];
        for(int i=0;i<K;i++){
            int[] nowCow=cows.get(i);
            visit=new boolean[N+1][N+1];
            visit[nowCow[0]][nowCow[1]]=true;
            dfs(nowCow[0],nowCow[1]);
            for(int j=i+1;j<K;j++){
                int[] cow=cows.get(j);
                if(!visit[cow[0]][cow[1]]){
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    static void dfs(int y,int x){

        for(int i=0;i<4;i++){
            int ny=y+dy[i];
            int nx=x+dx[i];
            if(ny>=1&&nx>=1&&ny<=N&&nx<=N&&!visit[ny][nx]){
                boolean flag=false;
                for(int[] next:map[y][x]){
                    if(next[0]==ny&&next[1]==nx){
                        flag=true;
                        break;
                    }
                }
                if(!flag) {
                    visit[ny][nx] = true;
                    dfs(ny, nx);
                }
            }
        }

    }
}