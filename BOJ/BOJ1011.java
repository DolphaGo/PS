import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int answer=0;
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int sqrt = (int)Math.sqrt(y-x);
            int length = y-x - sqrt*sqrt; // 남은 길이

            if(length>sqrt) answer= (sqrt-1)*2+3;
            else if(length==0) answer = (sqrt-1)*2+1;
            else answer= (sqrt-1)*2+2;
            sb.append(answer).append('\n');
        }
        System.out.print(sb);
    }
}
