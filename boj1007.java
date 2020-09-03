import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static double answer;
    static int[][] arr;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T=Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++){
            n=Integer.parseInt(br.readLine());
            arr=new int[n][2];
            visit=new boolean[n];
            for(int i=0;i<n;i++){
                st=new StringTokenizer(br.readLine());
                arr[i][0]=Integer.parseInt(st.nextToken());
                arr[i][1]=Integer.parseInt(st.nextToken());
            }

            answer=Integer.MAX_VALUE;
            go(arr,0,0);
            System.out.println(answer);
        }
    }

    static void go(int[][] arr,int start,int select){
        if(select==n/2){
            double X=0;
            double Y=0;
            for(int i=0;i<n;i++){
                if(visit[i]){
                    Y+=arr[i][0];
                    X+=arr[i][1];
                }else{
                    Y-=arr[i][0];
                    X-=arr[i][1];
                }
            }
            double dis=Math.sqrt(X*X+Y*Y);
            answer=Math.min(answer,dis);
            return;
        }

        for(int i=start;i<n;i++){
            visit[i]=true;
            go(arr,i+1,select+1);
            visit[i]=false;
        }
    }
}