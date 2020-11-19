import java.util.*;

class Solution3 {
    static List<Integer>[] list;
    public int solution(int n, int[][] edges) {
        int answer = 0;
        list=new ArrayList[n+1];
        for(int i=1;i<=n;i++) list[i]=new ArrayList<>();

        for(int i=0;i<edges.length;i++){
            int[] edge=edges[i];
            int a=edge[0];
            int b=edge[1];
            list[a].add(b);
            list[b].add(a);
        }

        //트리의 지름을 구하기
        //1에서 가장 먼 정점 a
        int a=bfs(1,n,0)[0];
        //a에서 가장 먼 정점 b
        int b=bfs(a,n,0)[0];

        //a를 제외하고 트리의 지름
        int c=bfs(1,n,a)[0];
        int R1=bfs(c,n,a)[1];

        //b를 제외하고 트리의 지름
        int d=bfs(1,n,b)[0];
        int R2=bfs(d,n,b)[1];

        answer=Math.max(R1,R2);
        return answer;
    }
    static int[] bfs(int start,int n,int exception){
        boolean[] visit=new boolean[n+1];
        //제외할 것
        visit[exception]=true;

        visit[start]=true;
        Queue<int[]> q=new LinkedList<>();
        q.add(new int[]{start,0});
        int max=0;
        int end=0;
        while(!q.isEmpty()){
            int[] p=q.poll();
            int now=p[0];
            if(max<p[1]){
                max=p[1];
                end=p[0];
            }
            for(int next:list[now]){
                if(!visit[next]){
                    visit[next]=true;
                    q.add(new int[]{next,p[1]+1});
                }
            }
        }
        return new int[]{end,max};
    }
}