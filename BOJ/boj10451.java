import java.util.*;
import java.io.*;

public class Main {
    static int arr[];
    static boolean visit[];
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T=Integer.parseInt(br.readLine());

        for(int tc=1;tc<=T;tc++){
            int N=Integer.parseInt(br.readLine());
            arr=new int[N+1];
            visit=new boolean[N+1];
            st=new StringTokenizer(br.readLine());
            for(int i=1;i<=N;i++) arr[i]=Integer.parseInt(st.nextToken());

            int answer=0;
            for(int i=1;i<=N;i++){
                if(!visit[i]){
                    visit[i]=true;
                    go(arr[i]);
                    ++answer;
                }
            }
            System.out.println(answer);
        }
    }
    static void go(int v){
        if(!visit[v]){
            visit[v]=true;
            go(arr[v]);
        }
    }
}
