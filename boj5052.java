import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());

            //문자열 입력
            String[] s=new String[n];
            for(int i=0;i<n;i++) {
                s[i] = br.readLine();
            }

            //사전순으로 정렬
            Arrays.sort(s);

            boolean res=true;
            for(int i=0;i<s.length-1;i++){
                if(s[i+1].startsWith(s[i])){
                    res=false;
                    break;
                }
            }

            if(res) sb.append("YES");
            else sb.append("NO");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}