import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());
        int B=Integer.parseInt(st.nextToken());

        int[] arr=new int[N+1];
        for(int i=0;i<B;i++){
            int broken=Integer.parseInt(br.readLine());
            arr[broken]=1;
        }

        int cnt=0;
        for(int i=1;i<=K;i++) cnt+=arr[i];
        int answer=cnt;

        for(int i=K+1;i<=N;i++){
            cnt-=arr[i-K];
            cnt+=arr[i];
            answer=Math.min(answer,cnt);
        }
        System.out.println(answer);
    }
}