import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[] tmp;
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        st=new StringTokenizer(br.readLine());
        int[] arr=new int[n];
        for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        tmp=new int[m];
        go(arr,0,0);
        System.out.print(sb);
    }
    static void go(int[] arr,int st,int v){
        if(v==m){
            for(int i=0;i<m;i++){
                sb.append(tmp[i]+" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=st;i<n;i++){
            tmp[v]=arr[i];
            go(arr,i,v+1);
        }
    }
}