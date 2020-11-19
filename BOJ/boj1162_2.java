import java.io.*;
import java.util.*;

public class Main {
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
        PriorityQueue<long[]> pq=new PriorityQueue<>(new Comparator<long[]>(){
            public int compare(long[] o1,long[] o2){
                return Long.compare(o1[0],o2[0]);
            }
        });

        // [0] : 비용 , [1] : 현재 위치 , [2] : 포장 횟수
        pq.add(new long[]{0,1,0});
        boolean[][] visit=new boolean[n+1][k+1];
        while(!pq.isEmpty()){
            long[] p=pq.poll();
            long cost=p[0];
            int cur=(int)p[1];
            int use=(int)p[2];
            if(visit[cur][use]) continue;
            visit[cur][use]=true;

            if(cur==n){
                System.out.println(cost);
                return;
            }

            for(int i=0;i<list[cur].size();i++){
                int[] val=list[cur].get(i);
                int next=val[0];
                int c=val[1];

                pq.add(new long[]{cost+c,next,use});
                if(use<k) pq.add(new long[]{cost,next,use+1});
            }
        }
    }
}