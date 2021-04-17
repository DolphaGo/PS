import java.util.*;

class Solution {
    static int[] in;
    static void dfs(List<Integer>[] list, long[] a, int now, int par){
        if(par != -1) in[par]++;
        for(int i=0;i<list[now].size();i++){
            int next=list[now].get(i);
            if(next!=par){
                dfs(list, a, next, now);
            }
        }
    }

    public long solution(int[] a, int[][] edges) {
        long[] longA=new long[a.length];
        long check =0;
        for(int i=0;i<a.length;i++){
            check += a[i];
            longA[i] = a[i];
        }
        if(check !=0L) return -1L;

        in = new int[a.length];
        List<Integer> list[]= new ArrayList[a.length];
        for(int i=0;i<a.length;i++){
            list[i]=new ArrayList<>();
        }

        for(int i=0;i<edges.length;i++){
            int[] e = edges[i];
            list[e[0]].add(e[1]);
            list[e[1]].add(e[0]);
        }

        dfs(list, longA, 0,-1);

        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<a.length;i++){
            if(in[i]==0){
                in[i]=-1; // 방문 처리
                q.add(i);
            }
        }

        long answer = 0;
        while(!q.isEmpty()){
            int p = q.poll();
            int par = getParent(list, p);
            if(par == -1) break;
            longA[par] += longA[p];
            answer += Math.abs(longA[p]);
            if(--in[par]==0){
                in[par]=-1; // 방문 처리
                q.add(par);
            }
        }
        return answer;
    }
    static int getParent(List<Integer>[] list, int p){
        for(int i=0;i<list[p].size();i++){
            int next=list[p].get(i);
            if(in[next]>0) {
                return next;
            }
        }
        return -1;
    }
}