import java.io.*;
import java.util.*;

public class Main {
    static int n,r,q;
    static List<Integer>[] list;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        r=Integer.parseInt(st.nextToken());
        q=Integer.parseInt(st.nextToken());
        list=new ArrayList[n+1];
        for(int i=1;i<=n;i++) list[i]=new ArrayList<>();

        for(int i=0;i<n-1;i++){
            st=new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }
        dp=new int[n+1];
        go(0,r);
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<q;i++){
            int u=Integer.parseInt(br.readLine());
            sb.append(dp[u]).append("\n");
        }
        System.out.print(sb);
    }
    static int go(int prev,int cur){
        if(dp[cur]!=0) return dp[cur];
        dp[cur]=1;
        for(int next:list[cur]){
            if(prev==next) continue; //부모는 어차피 1명+사이클 없으므로 가능 if로 간단하게 커팅 가능
            dp[cur]+=go(cur,next);
        }
        return dp[cur];
    }
}