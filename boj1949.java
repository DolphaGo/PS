import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static int[][] dp;
    static List<Integer>[] list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n=Integer.parseInt(br.readLine());
        arr=new int[n+1];
        st=new StringTokenizer(br.readLine());
        list=new ArrayList[n+1];
        dp=new int[n+1][2];

        for(int i=1;i<=n;i++) {
            arr[i]=Integer.parseInt(st.nextToken());
            list[i]=new ArrayList<>();
            Arrays.fill(dp[i],-1);
        }

        for(int i=0;i<n-1;i++){
            st=new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }

        System.out.println(Math.max(go(0,1,0),go(0,1,1)));
    }
    static int go(int prev, int now,int state){
        if(dp[now][state]!=-1) return dp[now][state];
        dp[now][state]=0;

        int res=0;
        //부모가 우수마을이 아니라면 자식 중 하나는 우수마을이어야 함
        if(state==0) {
            for (int next : list[now]) {
                if (next == prev) continue;
                res+=Math.max(go(now,next,0),go(now,next,1));
            }
        }else{//부모가 우수마을이라면 자식은 우수마을일 수 없음
            res=arr[now];
            for(int next:list[now]){
                if(next==prev) continue;
                res+=go(now,next,0);
            }
        }
        return dp[now][state]=res;
    }
}