import java.io.*;
import java.util.*;

public class Main {
    static int v;
    static List<int[]>[] list;
    static Queue<int[]> q=new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        v=Integer.parseInt(br.readLine());
        list=new ArrayList[v+1];
        for(int i=1;i<=v;i++){
            st=new StringTokenizer(br.readLine());
            int cur=Integer.parseInt(st.nextToken());
            list[cur]=new ArrayList<>();
            while(st.hasMoreTokens()) {
                int next = Integer.parseInt(st.nextToken());
                if(next==-1) break;
                int cost=Integer.parseInt(st.nextToken());
                list[cur].add(new int[]{next, cost});
            }
        }

        int x=bfs(1)[0];
        System.out.println(bfs(x)[1]);
    }
    static int[] bfs(int start){
        boolean[] visit=new boolean[v+1];
        visit[start]=true;
        q.add(new int[]{start,0});
        int end=0;
        int max=0;
        while(!q.isEmpty()){
            int[] p=q.poll();
            int now=p[0];
            if(max<p[1]){
                max=p[1];
                end=p[0];
            }
            for(int[] info:list[now]){
                int next=info[0];
                int cost=info[1];
                if(!visit[next]){
                    visit[next]=true;
                    q.add(new int[]{next,p[1]+cost});
                }
            }
        }
        return new int[]{end,max};
    }
}