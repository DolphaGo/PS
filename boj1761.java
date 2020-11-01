import java.io.*;
import java.util.*;

public class Main {
    static int[] depth,len;
    static int[][] dp;
    static List<int[]>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        list=new ArrayList[n+1];
        for(int i=1;i<=n;i++) list[i]=new ArrayList<>();
        for(int i=0;i<n-1;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new int[]{b, c});
            list[b].add(new int[]{a, c});
        }
        dp=new int[n+1][17];
        len=new int[n+1];
        depth=new int[n+1];
        dfs(0,1,0,0);
        connect(n);
        int m=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int lca=query(a,b);
            sb.append(len[a]+len[b]-2*len[lca]).append("\n");
        }
        System.out.print(sb);
    }
    static int query(int a,int b){
        if(depth[a]>depth[b]){
            int temp=a;
            a=b;
            b=temp;
        }

        for(int i=16;i>=0;i--){
            if(depth[b]-depth[a] >= (1<<i)){
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
        for(int d=1;d<=16;d++){
            for(int cur=1;cur<=n;cur++){
                dp[cur][d]=dp[dp[cur][d-1]][d-1];
            }
        }
    }

    static void dfs(int prev,int cur,int d,int dis){
        depth[cur]=d;
        len[cur]=dis;
        for(int[] info:list[cur]){
            int next=info[0];
            int cost=info[1];
            if(next==prev) continue;
            dp[next][0]=cur;
            dfs(cur,next,d+1,dis+cost);
        }
    }
}