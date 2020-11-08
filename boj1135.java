import java.io.*;
import java.util.*;

class Main{
    static List<Info>[] list;
    static class Info implements Comparable<Info> {
        int node;
        int time;
        Info(int node,int time){
            this.node=node;
            this.time=time;
        }
        @Override
        public int compareTo(Info o) {
            return Integer.compare(o.time,this.time);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        st=new StringTokenizer(br.readLine());
        list=new ArrayList[n];
        for(int i=0;i<n;i++){
            list[i]=new ArrayList<>();
            int master=Integer.parseInt(st.nextToken());
            if(master==-1) continue;
            list[master].add(new Info(i,0));
        }
        System.out.println(dfs(0));
    }
    static int dfs(int cur){
        int max=0,p=0;

        for(Info i : list[cur]){
            i.time = dfs(i.node);
        }
        Collections.sort(list[cur]);
        for(Info next : list[cur]){
            max=Math.max(max,next.time+(++p));
        }
        return max;
    }
}