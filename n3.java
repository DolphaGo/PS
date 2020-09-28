import java.util.*;

public class Main {
    static int n,answer;
    static boolean[] check;
    static List<Integer>[] list,depth;
    static void makeDepth(){
        Queue<int[]> q=new LinkedList<>();
        q.add(new int[]{0,0});
        depth[0].add(0);
        while(!q.isEmpty()){
            int[] p=q.poll();
            int now=p[0];
            int d=p[1];
            for(int next:list[now]){
                depth[d+1].add(next);
                q.add(new int[]{next,d+1});
            }
        }
    }

    public static void main(String[] args)  {
        n=10;
        int edges[][]={
                {0,1},
                {0,2},
                {1,3},
                {2,4},
                {2,5},
                {2,6},
                {3,7},
                {3,8},
                {3,9}
        };

        answer=Integer.MAX_VALUE;

        list=new ArrayList[n];
        depth=new ArrayList[n];
        check=new boolean[n];

        for(int i=0;i<n;i++) {
            list[i]=new ArrayList<>();
            depth[i]=new ArrayList<>();
        }
        for(int i=0;i< edges.length;i++){
            int[] edge=edges[i];
            int p=edge[0];
            int c=edge[1];
            list[p].add(c);
        }
        makeDepth();
        solve(1);//루트(depth:0)는 항상 감염이라서 depth 1부터 선택했어요.
        System.out.println(answer);
    }

    static void solve(int d){
        if(depth[d].size()==0){
            int res=simulation();
            answer=Math.min(res,answer);
            return;
        }
        for(int val:depth[d]){
            check[val]=true;
            solve(d+1); //백트래킹으로 depth 별로 하나씩 선택합니다.
            check[val]=false;
        }
    }

    static int simulation(){
        Queue<Integer> q=new LinkedList<>();
        q.add(0);
        int ret=1;//루트는 항상 감염이니까 1로 시작할게요.
        while(!q.isEmpty()){
            int now=q.poll();
            for(int next:list[now]){
                if(check[next]) continue; //살리는 애들은 제외
                ++ret; //감염된 수를 세줍니다.
                q.add(next);  //감염된 것
            }
        }
        return ret;
    }
}