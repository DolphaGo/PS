import java.io.*;
import java.util.*;

public class Main {
    static int n,far,max;
    static List<int[]>[] list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n=Integer.parseInt(br.readLine());
        list=new ArrayList[n+1];
        for(int i=1;i<=n;i++) list[i]=new ArrayList<>();
        for(int i=0;i<n-1;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            list[a].add(new int[]{b,c});
            list[b].add(new int[]{a,c});
        }

        //임의의 점을 기준으로 가장 먼 지점 Get
        max=0;
        go(0,1,0,0);
        int C=far;

        //이 지점(C)에서 가장 먼 거리 -> 트리의 지름
        max=0;
        go(0,C,0,0);
        int S=far;

        //S를 제외하고 C에서 시작할 때 가장 먼거리 -> 트리의 두 번째 지름 후보 1
        max=0;
        go(0,C,0,S);
        int len1=max;

        //C를 제외하고 S에서 시작할 때 가장 먼 거리 -> 트리의 두 번째 지름 후보 2
        max=0;
        go(0,S,0,C);
        int len2=max;

        System.out.println(Math.max(len1,len2));
    }
    static void go(int prev,int cur,int cost,int except){
        if(max<cost){
            max=cost;
            far=cur;
        }
        for(int[] info:list[cur]){
            int next=info[0];
            int c=info[1];
            if(next==prev || next==except) continue;
            go(cur,next,cost+c,except);
        }
    }
}