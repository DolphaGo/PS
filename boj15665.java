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
        TreeSet<Integer> set=new TreeSet<>();
        for(int i=0;i<n;i++){
            int num=Integer.parseInt(st.nextToken());
            set.add(num);
        }
        tmp=new int[m];
        go(set,0);
        System.out.print(sb);
    }
    static void go(TreeSet<Integer> set,int v){
        if(v==m){
            for(int i=0;i<m;i++) sb.append(tmp[i]+" ");
            sb.append("\n");
            return;
        }

       for(int val:set){
           tmp[v]=val;
           go(set,v+1);
       }
    }
}