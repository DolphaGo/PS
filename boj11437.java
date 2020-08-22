import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> list[];
    static boolean visit[];
    static int[] d;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n=Integer.parseInt(br.readLine());
        list=new ArrayList[n+1];
        for(int i=1;i<=n;i++) list[i]=new ArrayList<>();

        for(int i=0;i<n-1;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        dp=new int[n+1][17];
        visit=new boolean[n+1];
        d=new int[n+1];

        dp[1][0]=1; // 예외 , 1의 부모는 1.
        dfs(1,0);
        connect(n);

        StringBuilder sb=new StringBuilder();
        int m=Integer.parseInt(br.readLine());

        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            sb.append(LCA(a,b)).append("\n");
        }
        System.out.println(sb);
    }

    static int LCA(int a,int b){
        if(d[a]>d[b]){
            int temp=a; a=b; b=temp;
        }

        for(int i=16;i>=0;i--){
            if(d[b]-d[a]>=(1<<i)){
                b=dp[b][i];
            }
        }
        if(a==b) return a;

        for(int i=16;i>=0;i--){
            if(dp[a][i]==dp[b][i]) continue;

            a=dp[a][i];
            b=dp[b][i];
        }

        return dp[a][0];
    }

    static void connect(int n){
        for(int depth=1;depth<=15;depth++){
            for(int cur=1;cur<=n;cur++){
                dp[cur][depth]=dp[dp[cur][depth-1]][depth-1];
            }
        }
    }

    static void dfs(int cur,int depth){
        visit[cur]=true;
        d[cur]=depth;

        for(int next:list[cur]){
            if(!visit[next]){
                dp[next][0]=cur;
                dfs(next,depth+1);
            }
        }
    }
}