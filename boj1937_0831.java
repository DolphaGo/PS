import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    static int[][] dp,map;
    static int n,answer;
    static int dy[]={-1,1,0,0};
    static int dx[]={0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n=Integer.parseInt(br.readLine());

        map=new int[n][n];
        dp=new int[n][n];

        for(int y=0;y<n;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<n;x++){
                map[y][x]=Integer.parseInt(st.nextToken());
            }
        }
        answer=0;
        for(int y=0;y<n;y++){
            for(int x=0;x<n;x++){
                if(dp[y][x]==0) go(y,x);
            }
        }
        System.out.println(answer);
    }
    static int go(int y,int x){
        if(dp[y][x]!=0) return dp[y][x];
        dp[y][x]=1;

        for(int i=0;i<4;i++){
            int ny=y+dy[i];
            int nx=x+dx[i];
            if(isRange(ny,nx)&&map[ny][nx]>map[y][x]){
                dp[y][x]=Math.max(go(ny,nx)+1,dp[y][x]);
            }
        }
        answer=Math.max(dp[y][x],answer);
        return dp[y][x];
    }
    static boolean isRange(int y,int x){
        return 0<=x&&x<n&&0<=y&&y<n;
    }
}