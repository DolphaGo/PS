import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp,map;
    static int h,w;
    static int dy[]={1,0,1};
    static int dx[]={0,1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        h=Integer.parseInt(st.nextToken());
        w=Integer.parseInt(st.nextToken());

        map=new int[h][w];
        dp=new int[h][w];

        for(int y=0;y<h;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<w;x++){
                map[y][x]=Integer.parseInt(st.nextToken());
                dp[y][x]=-1;
            }
        }
        System.out.println(go(0,0));

    }
    static int go(int y,int x){
        if(dp[y][x]!=-1) return dp[y][x];
        dp[y][x]=map[y][x];
        
        for(int i=0;i<3;i++){
            int ny=y+dy[i];
            int nx=x+dx[i];
            if(isRange(ny,nx)){
                dp[y][x]=Math.max(dp[y][x],map[y][x]+go(ny,nx));
            }
        }
        return dp[y][x];
    }
    static boolean isRange(int y,int x){
        return 0<=x&&x<w&&0<=y&&y<h;
    }
}