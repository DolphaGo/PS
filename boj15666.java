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

        ArrayList<Integer> list=new ArrayList<>(set);
        tmp=new int[m];
        go(list,0,0);
        System.out.print(sb);
    }
    static void go(ArrayList<Integer> list,int st,int v){
        if(v==m){
            for(int i=0;i<m;i++) sb.append(tmp[i]+" ");
            sb.append("\n");
            return;
        }

       for(int i=st;i<list.size();i++){
           int val=list.get(i);
           tmp[v]=val;
           go(list,i,v+1);
       }
    }
}