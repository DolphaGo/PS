import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());

        char[][] map=new char[n][n];
        for(int y=0;y<n;y++){
            map[y]=br.readLine().toCharArray();
        }

        int max=0;
        Queue<Integer> q=new LinkedList<>();
        boolean[] visit=new boolean[n];
        for(int i=0;i<n;i++){
            int cnt=0;
            q.add(i);
            visit[i]=true;

            for(int t=0;t<2;t++) {
                int qsize = q.size();
                for (int k = 0; k < qsize; k++) {
                    int p = q.poll();
                    for (int j = 0; j < n; j++) {
                        if (map[p][j] == 'Y' && !visit[j]) {
                            q.add(j);
                            visit[j] = true;
                            ++cnt;
                        }
                    }
                }
            }

            max=Math.max(max,cnt);
            Arrays.fill(visit,false);
            q.clear();
        }

        System.out.println(max);
    }
}
