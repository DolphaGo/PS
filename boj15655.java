import java.util.*;
import java.io.*;

public class Main {
    static int n,m;
    static int[] arr;
    static int[] select;
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());

        arr=new int[n];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        select=new int[m];
        go(0,0);
        System.out.print(sb);
    }
    static void go(int start,int se){
        if(se==m) {
            for(int i=0;i<m;i++) sb.append(select[i]+" ");
            sb.append("\n");
            return;
        }

        for(int i=start;i<n;i++){
            select[se]=arr[i];
            go(i+1,se+1);
        }



    }
}
