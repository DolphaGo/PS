import java.util.*;
import java.io.*;

public class Main {
    static int K,N;
    static long[] lines;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        K=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());

        long s=1;
        long e=1;
        lines=new long[K];
        for(int i=0;i<K;i++){
            lines[i]=Integer.parseInt(br.readLine());
            e=Math.max(e,lines[i]);
        }

        long answer=1;
        while(s<=e){
            long m=(s+e)>>1;
            if(test(m)>=N){
                answer=Math.max(answer,m);
                s=m+1;
            }else e=m-1;
        }
        System.out.println(answer);
    }
    static int test(long len){
        int res=0;
        for(int i=0;i<K;i++){
            res+=lines[i]/len;
        }
        return res;
    }
}