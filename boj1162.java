import java.io.*;
import java.util.*;

public class Main {
    static class Info{
        int cur,use;
        long cost;
        public Info(int cur,int use,long cost){
            this.cur=cur;
            this.use=use;
            this.cost=cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());

        ArrayList<int[]> list[]=new ArrayList[n+1];
        for(int i=1;i<=n;i++) list[i]=new ArrayList<>();

        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            list[a].add(new int[]{b,c});
            list[b].add(new int[]{a,c});
        }
        PriorityQueue<Info> pq=new PriorityQueue<>(new Comparator<Info>(){
            public int compare(Info o1,Info o2){
                return Long.compare(o1.cost,o2.cost);
            }
        });

        pq.add(new Info(1,0,0));
        boolean[][] visit=new boolean[n+1][k+1];
        while(!pq.isEmpty()){
            Info p=pq.poll();
            if(visit[p.cur][p.use]) continue;
            visit[p.cur][p.use]=true;

            if(p.cur==n){
                System.out.println(p.cost);
                return;
            }

            for(int i=0;i<list[p.cur].size();i++){
                int[] val=list[p.cur].get(i);
                int next=val[0];
                int c=val[1];

                pq.add(new Info(next,p.use,p.cost+c));
                if(p.use<k) pq.add(new Info(next,p.use+1,p.cost));
            }
        }
    }
}