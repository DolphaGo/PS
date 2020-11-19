import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        boolean[][] arr=new boolean[n+1][n+1];

        while(true){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            if(a==-1&&b==-1) break;
            arr[a][b]=arr[b][a]=true;
        }


        int score[]=new int[n+1];
        int min=Integer.MAX_VALUE;
        boolean visit[]=new boolean[n+1];

        Queue<Integer> q=new LinkedList<>();
        for(int i=1;i<=n;i++) {
            visit[i] = true;
            q.add(i);
            int friends=1;
            int t=0;
            while(friends<n) {
                ++t;
                int size = q.size();
                while (size-- > 0) {
                    int p = q.poll();
                    for (int j = 1; j <= n; j++) {
                        if (arr[p][j]&&!visit[j]) {
                            visit[j] = true;
                            q.add(j);
                            ++friends;
                        }
                    }
                }
            }
            score[i]=t;
            min=min>t?t:min;
            q.clear();
            Arrays.fill(visit,false);
        }
        StringBuilder sb=new StringBuilder();
        int cnt=0;
        for(int i=1;i<=n;i++){
            if(score[i]==min){
                ++cnt;
                sb.append(i+" ");
            }
        }
        System.out.println(min+" "+cnt);
        System.out.print(sb.toString());
    }
}

