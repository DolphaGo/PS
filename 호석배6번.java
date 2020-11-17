import java.io.*;
import java.util.*;

class Main {
    static List<int[]>[] list;
    static int answer;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int a=Integer.parseInt(st.nextToken());
        int b=Integer.parseInt(st.nextToken());
        int c=Integer.parseInt(st.nextToken());

        list=new ArrayList[n+1];
        visit=new boolean[n+1];
        for(int i=1;i<=n;i++) list[i]=new ArrayList<>();

        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            int k=Integer.parseInt(st.nextToken());
            list[x].add(new int[]{y,k});
            list[y].add(new int[]{x,k});
        }

        answer=Integer.MAX_VALUE;
        visit[a]=true;
        dfs(a,b,c,0);
        visit[a]=false;
        System.out.println(answer==Integer.MAX_VALUE?-1:answer);
    }
    static void dfs(int cur,int dst,int money,int max){
        if(cur==dst){
            answer=Math.min(answer,max);
            return;
        }

        for(int[] info:list[cur]){
            int next=info[0];
            int cost=info[1];
            if(visit[next]) continue; //이미 방문한 곳은 안감
            if(money<cost) continue; //돈 없어서 못갈 때
            visit[next]=true;
            int nextMax=Math.max(max,cost);
            dfs(next,dst,money-cost,nextMax);
            visit[next]=false;
        }
    }
}