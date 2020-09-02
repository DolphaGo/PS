import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        ArrayList<int[]> list[]=new ArrayList[n+1];
        for(int i=1;i<=n;i++) list[i]=new ArrayList<>();
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            list[a].add(new int[]{b,c});
        }

        long[] dist=new long[n+1];
        Arrays.fill(dist,Long.MAX_VALUE/2);
        dist[1]=0;

        boolean isCycle=false;

        for(int i=1;i<=n;i++){
            //모든 간선에 대해 길이를 업데이트 해주는 작업을 합니다.
            for(int src=1;src<=n;src++) {
                //1번에서 시작합니다. 현재까지의 경로가 존재하지 않는데 연산하는 경우는 경로가 없다는 뜻
                if(dist[src]==Long.MAX_VALUE/2) continue;
                for (int j = 0; j < list[src].size(); j++) {
                    int[] val = list[src].get(j);
                    int dst = val[0];
                    int weight = val[1];
                    if (dist[dst] > dist[src] + weight) {
                        dist[dst] = dist[src] + weight;
                        if(i==n)  isCycle=true;
                    }
                }
            }
        }

        if(isCycle) System.out.println(-1);
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i <= n; i++) {
                if (dist[i] == Long.MAX_VALUE / 2) sb.append(-1);
                else sb.append(dist[i]);
                sb.append("\n");
            }
            System.out.print(sb);
        }
    }
}