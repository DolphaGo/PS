import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] dist,dp;
    static List<int[]>[] list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        list=new ArrayList[n+1];
        for(int i=1;i<=n;i++) list[i]=new ArrayList<>();
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            list[a].add(new int[]{b,c});
            list[b].add(new int[]{a,c});
        }
        dist =new int[n+1];
        dijkstra(2);

        dp=new int[n+1];
        Arrays.fill(dp,-1);
        System.out.println(go(1));
    }
    static int go(int cur){
        if(dp[cur]!=-1) return dp[cur];
        if(cur==2) return dp[cur]=1;
        dp[cur]=0;
        for(int[] info:list[cur]){
            int next=info[0];
            if(dist[cur]>dist[next]){ //합리적으로 가고 있다면
                dp[cur]+= go(next);
            }
        }
        return dp[cur];
    }
    static void dijkstra(int start){
        Arrays.fill(dist,Integer.MAX_VALUE);
        PriorityQueue<int[]> q=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1],o2[1]);
            }
        });
        dist[start]=0;
        q.add(new int[]{start,0});
        while(!q.isEmpty()){
            int[] p=q.poll();
            int now=p[0];
            int d=p[1];
            if(d>dist[now]) continue;

            for(int[] info:list[now]){
                int next=info[0];
                int cost=info[1];
                if(dist[next]>dist[now]+cost){
                    dist[next]=dist[now]+cost;
                    q.add(new int[]{next,dist[next]});
                }
            }
        }
    }
}