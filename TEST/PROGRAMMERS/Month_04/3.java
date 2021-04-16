import java.util.*;

class Solution {
    static long dfs(List<Integer>[] list, long[] a, int now, int par){
        long res = 0;
        for(int i=0;i<list[now].size();i++){ // for(int next:list[now]) 는 런타임 에러
            int next=list[now].get(i);
            if(next!=par){
                res += dfs(list, a, next, now);
            }
        }
        if(par!=-1) a[par]+=a[now];
        return res + Math.abs(a[now]);
    }

    public long solution(int[] a, int[][] edges) {
        long[] longA=new long[a.length];
        long check =0;
        for(int i=0;i<a.length;i++){
            check += a[i];
            longA[i] = a[i];
        }
        if(check !=0L) return -1L;

        List<Integer> list[]= new ArrayList[a.length];
        for(int i=0;i<a.length;i++){
            list[i]=new ArrayList<>();
        }

        for(int i=0;i<edges.length;i++){
            int[] e = edges[i];
            list[e[0]].add(e[1]);
            list[e[1]].add(e[0]);
        }

        return dfs(list, longA, 0,-1);
    }
}