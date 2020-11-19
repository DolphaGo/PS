import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp;
    static List<Integer>[] list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        int n=Integer.parseInt(br.readLine());
        list=new ArrayList[n+1];
        for(int i=1;i<=n;i++) list[i]=new ArrayList<>();

        for(int i=0;i<n-1;i++){
            st=new StringTokenizer(br.readLine());

            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }

        dp=new int[n+1][2];
        for(int i=1;i<=n;i++) Arrays.fill(dp[i],-1);
        System.out.println(Math.min(go(0,1,0),go(0,1,1)));
    }
    static int go(int prev,int cur,int state){
        if(dp[cur][state]!=-1) return dp[cur][state];
        int res=0;
        
        //만약 현재 cur가 얼리어답터가 아닐 때
        if(state==0){
            for(int next:list[cur]) { //자식들은 반드시 얼리어답터여야 한다.
                if (prev == next) continue;
                res += go(cur, next, 1);
            }
        }else{ //현재 cur가 얼리어답터일 때
            res=1;
            for(int next:list[cur]){
                if(prev==next) continue;
                res+=Math.min(go(cur,next,0),go(cur,next,1));
            }
        }
        return dp[cur][state]=res;
    }
}