import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        boolean flag=false;
        int start=0,end=0;
        for (int len = l; len <= 100; len++) {
            int x=n/len - (len-1)/2;

            if(x<0) break;

            int sum=0;
            for(int i=0;i<len;i++) sum+=(x+i);
            if(sum==n){
                flag=true;
                start=x;
                end=start+len-1;
                break;
            }
        }
        if(flag){
            StringBuilder sb=new StringBuilder();
            for(int i=start;i<=end;i++) sb.append(i+" ");
            System.out.println(sb);
        }else System.out.println(-1);
    }
}