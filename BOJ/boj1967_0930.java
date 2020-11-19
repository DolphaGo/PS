import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<int[]>[] list;
    static int answer;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        list=new ArrayList[n+1];
        for(int i=1;i<=n;i++) list[i]=new ArrayList<>();

        for(int i=0;i<n-1;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            list[a].add(new int[]{b,c});
            list[b].add(new int[]{a,c});
        }
        answer=0;
        visit=new boolean[n+1];
        for(int i=1;i<=n;i++) {
            visit[i]=true;
            dfs(i,0);
            visit[i]=false;
        }
        System.out.println(answer);
    }
    static void dfs(int cur,int len){
        answer=Math.max(answer,len);

        for(int[] nxt:list[cur]){
            int next=nxt[0];
            int cost=nxt[1];
            if(!visit[next]){
                visit[next]=true;
                dfs(next,len+cost);
                visit[next]=false;
            }
        }

    }
}