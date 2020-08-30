import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n=Integer.parseInt(br.readLine());
        map=new int[n][n];
        dp=new long[n][n];
        for(int y=0;y<n;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<n;x++){
                map[y][x]=Integer.parseInt(st.nextToken());
                dp[y][x]=-1;
            }
        }
        System.out.println(go(0,0));
    }
    static int dy[]={1,0};
    static int dx[]={0,1};
    static long go(int y,int x){
        if(y==n-1&&x==n-1) return dp[y][x]=1;
        if(dp[y][x]!=-1) return dp[y][x];

        dp[y][x]=0;
        int cur=map[y][x];

        for(int i=0;i<2;i++){
            int ny=y+cur*dy[i];
            int nx=x+cur*dx[i];
            if(isRange(ny,nx)) dp[y][x]+=go(ny,nx);
        }
        return dp[y][x];
    }
    static boolean isRange(int y,int x){
        return 0<=x&&x<n&&0<=y&&y<n;
    }
}