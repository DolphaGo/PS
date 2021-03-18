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
            int length= y-x;
            int n = upperBound(length);
            int res = length - n*(n+1);
            if(res==0) answer=2*n;
            else if(res <= n+1) answer= 2*n+1;
            else answer = 2*n+2;

            sb.append(answer).append('\n');
        }
        System.out.print(sb);
    }

    static int upperBound(int length){
        long s=1;
        long e=(long)Math.sqrt(Math.pow(2,32));
        while(s<e){
            long m=(s+e)>>1;
            if(m*(m+1)<=length) s=m+1;
            else e=m;
        }
        return (int)e-1;
    }
}
