import java.io.*;
import java.util.*;

public class Main {
    static final int MAX=100000;
    static boolean[] visit;
    static Queue<int[]> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int a=Integer.parseInt(st.nextToken());
        int b=Integer.parseInt(st.nextToken());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        int next,answer=0;
        visit=new boolean[MAX+1];
        visit[n]=true;
        q=new LinkedList<>();
        q.add(new int[]{n,0});
        while(!q.isEmpty()){
            int[] p=q.poll();
            int now=p[0];
            int cost=p[1];
            if(now==m){
                answer=cost;
                break;
            }
            for(int i=-1;i<=1;i+=2){
                next=now+i;
                check(next,cost+1);

                next=now+(a*i);
                check(next,cost+1);

                next=now+(b*i);
                check(next,cost+1);
            }
            next=now*a;
            check(next,cost+1);

            next=now*b;
            check(next,cost+1);
        }
        System.out.println(answer);
    }
    static boolean isRange(int loc){
        return 0<=loc&&loc<=MAX;
    }

    static void check(int next,int cost){
        if(isRange(next)&&!visit[next]) {
            visit[next] = true;
            q.add(new int[]{next, cost});
        }
    }
}