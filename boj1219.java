import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int start=Integer.parseInt(st.nextToken());
        int dest=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        ArrayList<int[]> list[]=new ArrayList[n];
        for(int i=0;i<n;i++) list[i]=new ArrayList<>();
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            list[a].add(new int[]{b,-c});
        }

        int[] earn=new int[n];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) earn[i]=Integer.parseInt(st.nextToken());

        long[] dist=new long[n];
        Arrays.fill(dist,Long.MIN_VALUE/2);
        dist[start]=earn[start];

        boolean isGee=false;
        for(int iter=1;iter<=n;iter++){
            for(int cur=0;cur<n;cur++){
                if(dist[cur]==Long.MIN_VALUE/2) continue; //갱신이 안된 곳은 갈 수 없음
                for(int k=0;k<list[cur].size();k++){
                    int[] val=list[cur].get(k);
                    int next=val[0];
                    int cost=val[1];
                    if(dist[next]<earn[next]+dist[cur]+cost){
                        dist[next]=earn[next]+dist[cur]+cost;
                        if(iter==n){ //음수 사이클이 존재할 때 도착지점을 갈 수 있는지 판단.
                            isGee=geeTest(list,next,dest,n);
                            if(isGee){
                                System.out.println("Gee");
                                return;
                            }
                        }
                    }
                }
            }
        }

        long res=dist[dest];
        if(res==Long.MIN_VALUE/2) System.out.println("gg");
        else System.out.println(res);
    }
    static boolean geeTest(ArrayList<int[]> list[],int start,int dest,int n){
        boolean res=false;
        Queue<Integer> q=new LinkedList<>();
        boolean[] visit=new boolean[n];
        visit[start]=true;
        q.add(start);
        while(!q.isEmpty()){
            int cur=q.poll();
            if(cur==dest) return true;
            for(int i=0;i<list[cur].size();i++){
                int next=list[cur].get(i)[0];
                if(!visit[next]){
                    visit[next]=true;
                    q.add(next);
                }
            }
        }
        return false;
    }
}