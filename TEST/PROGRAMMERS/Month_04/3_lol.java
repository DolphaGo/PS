import java.util.*;

class Solution {
    static int o;
    static long[] sum;
    static int[] l,r;
    static List<Integer> list[];
    static long dfs(int[] a, int now){
        l[now] = ++o;
        sum[l[now]]=a[now];
        for(int next:list[now]){
            if(l[now]!=-1){
                sum[l[now]]+=dfs(a, next);
            }
        }
        r[now] = o;
        return sum[l[now]];
    }
    public long solution(int[] a, int[][] edges) {
        long check =0;
        for(int i=0;i<a.length;i++){
            check += a[i];
        }
        if(check !=0L) return -1;

        list=new ArrayList[a.length];
        for(int i=0;i<a.length;i++){
            list[i]=new ArrayList<>();
        }

        for(int i=0;i<edges.length;i++){
            int[] e = edges[i];
            list[e[0]].add(e[1]);
            list[e[1]].add(e[0]);
        }
        l = new int[a.length];
        Arrays.fill(l,-1);
        r= new int[a.length];
        sum = new long[a.length];
        o = -1;
        dfs(a,0);

        long answer = 0 ;
        for(int i=0;i<a.length;i++){
            for(int j=l[i];j<=r[i];j++){
                answer+=Math.abs(sum[j]-sum[i]);
            }
        }
        return answer;
    }
}