import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        int[][] arr=new int[n+1][n+1];
        for(int i=1;i<=n;i++) Arrays.fill(arr[i],Integer.MAX_VALUE/2);

        while(true){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            if(a==-1&&b==-1) break;
            arr[a][b]=1;
            arr[b][a]=1;
        }

        int score[]=new int[n+1];
        boolean visit[]=new boolean[n+1];
        int min=Integer.MAX_VALUE;
        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(arr[i][j]>arr[i][k]+arr[k][j]){
                        arr[i][j]=arr[i][k]+arr[k][j];
                    }
                }
            }
        }

        for(int i=1;i<=n;i++){
            int T=0;
            for(int j=1;j<=n;j++){
                if(i==j) continue;
                if(T<arr[i][j]) T=arr[i][j];
            }
            score[i]=T;
            min=Math.min(min,T);
        }

        StringBuilder sb=new StringBuilder();
        int cnt=0;
        for(int i=1;i<=n;i++){
            if(score[i]==min){
                ++cnt;
                sb.append(i+" ");
            }
        }
        System.out.println(min+" "+cnt);
        System.out.print(sb.toString());
    }
}

