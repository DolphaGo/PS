import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        TreeMap<String, Integer> map = new TreeMap<>();
        int[] p = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            String s = br.readLine();
            map.put(s, i);
            p[i] = i;
        }
        int win = 0, lose = 0, pwin=0, plose=0;
        for (int i = 0; i < m; i++) {
            String[] s = br.readLine().split(",");
            int result = Integer.parseInt(s[2]);
            if (result == 1) {
                win = map.get(s[0]);
                lose = map.get(s[1]);
            } else {
                win = map.get(s[1]);
                lose = map.get(s[0]);
            }
            pwin = getParent(p, win);
            plose= getParent(p,lose);

            if(pwin==plose&&pwin==lose){//속국이 종주국을 이겼을 때
                for(int j=1;j<=n;j++){
                    if(p[j]==plose) p[j]=win;
                }
            }else p[plose]=pwin;

        }
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            int num = map.get(key);
            if (num == p[num]) {
                ++cnt;
                sb.append(key).append("\n");
            }
        }
        System.out.println(cnt);
        System.out.print(sb);
    }

    static int getParent(int[] p, int a) {
        if (p[a] == a) return a;
        else return p[a] = getParent(p, p[a]);
    }
}