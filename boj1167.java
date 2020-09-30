import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int v=Integer.parseInt(br.readLine());
        List<int[]>[] list=new ArrayList[v+1];
        boolean[] hasParent=new boolean[v+1];
        for(int i=1;i<=v;i++){
            st=new StringTokenizer(br.readLine());
            int cur=Integer.parseInt(st.nextToken());
            list[cur]=new ArrayList<>();
            while(st.hasMoreTokens()) {
                int next = Integer.parseInt(st.nextToken());
                if(next==-1) break;
                int cost=Integer.parseInt(st.nextToken());

                //단방향으로 관리
                if(cur<next) {
                    list[cur].add(new int[]{next, cost});
                    hasParent[next]=true;
                }
            }
        }

        Queue<int[]> q=new LinkedList<>();
        //루트 노드를 찾아서 Queue에 넣고 시뮬을 돌려봅니다.
        for(int i=1;i<=v;i++){
            if(!hasParent[i]){
                q.add(new int[]{i,0});
                break;
            }
        }

        int answer=0;
        while(!q.isEmpty()){
            int[] p=q.poll();
            int cur=p[0];
            answer=Math.max(answer,p[1]);
            for(int[] info:list[cur]){
                int next=info[0];
                int cost=info[1];
            }
        }
        System.out.println(answer);
    }
}