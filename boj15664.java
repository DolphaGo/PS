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
        TreeMap<Integer,Integer> map=new TreeMap<>();
        for(int i=0;i<n;i++){
            int num=Integer.parseInt(st.nextToken());
            if(map.get(num)==null) map.put(num,1);
            else {
                int val=map.get(num);
                map.put(num,val+1);
            }
        }
        tmp=new int[m];
        ArrayList<Integer> list=new ArrayList<>(map.keySet());
        go(list,map,0,0);
        System.out.print(sb);
    }
    static void go(ArrayList<Integer> list,TreeMap<Integer,Integer> map,int st,int v){
        if(v==m){
            for(int i=0;i<m;i++) sb.append(tmp[i]+" ");
            sb.append("\n");
            return;
        }

        for(int i=st;i<list.size();i++){
            int key=list.get(i);
            if(map.get(key)>0){
                int value=map.get(key);
                map.put(key,value-1);
                tmp[v]=key;
                go(list,map,i,v+1);
                map.put(key,value);
            }
        }
    }
}