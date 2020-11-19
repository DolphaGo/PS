import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());

        ArrayList<int[]> list[];

        for(int tc=1;tc<=T;tc++){
            st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());

            list=new ArrayList[n+1];
            for(int i=1;i<=n;i++) list[i]=new ArrayList<>();

            //도로
            for(int i=0;i<m;i++){
                st=new StringTokenizer(br.readLine());
                int s=Integer.parseInt(st.nextToken());
                int e=Integer.parseInt(st.nextToken());
                int t=Integer.parseInt(st.nextToken());
                list[s].add(new int[]{e,t});
                list[e].add(new int[]{s,t});
            }

            //웜홀
            for(int i=0;i<w;i++){
                st=new StringTokenizer(br.readLine());
                int s=Integer.parseInt(st.nextToken());
                int e=Integer.parseInt(st.nextToken());
                int t=Integer.parseInt(st.nextToken());
                list[s].add(new int[]{e,-t});
            }

            boolean isCycle=BellmanFordLogic(list,n);

            if(isCycle) sb.append("YES");
            else sb.append("NO");
            sb.append("\n");

            for(int i=1;i<=n;i++) list[i].clear();
        }
        System.out.print(sb);
    }

    private static boolean BellmanFordLogic(ArrayList<int[]>[] list, int n) {
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        for (int k = 1; k <= n; k++) {

            if(dist[k]==Long.MAX_VALUE) dist[k]=0;

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < list[i].size(); j++) {
                    int[] val = list[i].get(j);
                    int next = val[0];
                    int cost = val[1];
                    if (dist[i]!=Long.MAX_VALUE&&dist[next] > dist[i] + cost) {
                        dist[next] = dist[i] + cost;
                        if (k == n) return true;
                    }
                }
            }
        }
        return false;
    }
}