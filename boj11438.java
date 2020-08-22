import java.io.*;
import java.util.*;

public class Main {
    static int n,k;
    static ArrayList<Integer> list[];
    static boolean[] visit;
    static int[] depth;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n=Integer.parseInt(br.readLine());
        list=new ArrayList[n+1];
        for(int i=1;i<=n;i++) list[i]=new ArrayList<>();

        for(int i=0;i<n-1;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        k=0;
        for(int i=n ; i>0 ; i/=2) k++;

        dp=new int[n+1][k+1];
        depth=new int[n+1];
        visit=new boolean[n+1];

        visit[1]=true;
        depth[1]=0;
        dp[1][0]=1;
        dfs(1,1);
        connect();

        int m=Integer.parseInt(br.readLine());

        StringBuilder sb=new StringBuilder();

        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());

            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());

            sb.append(LCA(a,b)).append("\n");
        }
        System.out.print(sb);
    }

    static int LCA(int a,int b){
        if(depth[a]>depth[b]){ int temp=a; a=b; b=temp; }

        for(int d=k;d>=0;d--){
            if(depth[b]-depth[a]>=(1<<d)){
                b=dp[b][d];
            }
        }

        if(b==a) return a;

        for(int d=k;d>=0;d--){
            if(dp[a][d]==dp[b][d]) continue;
            a=dp[a][d];
            b=dp[b][d];
        }
        return dp[a][0];
    }

    static void dfs(int cur,int d){
        for(int next:list[cur]){
            if(!visit[next]){
                visit[next]=true;
                depth[next]=d;
                dp[next][0]=cur;
                dfs(next,d+1);
            }
        }
    }
    static void connect(){
        for(int d=1;d<=k;d++){
            for(int node=1;node<=n;node++){
                dp[node][d]=dp[dp[node][d-1]][d-1];
            }
        }
    }
}
