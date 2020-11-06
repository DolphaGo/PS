import java.io.*;
import java.util.*;

public class Main {
    static final int n =2000000;
    static int[] tree;
    static int update(int node,int start,int end,int idx,int diff){
        if(idx>end || idx<start) return tree[node];
        if(start==end){
            return tree[node]+=diff;
        }
        int m=(start+end)>>1;
        return tree[node]=update(node<<1,start,m,idx,diff)+update(node<<1|1,m+1,end,idx,diff);
    }

    static int query(int node, int start, int end, int num){
        if(start==end) return start;
        int m=(start+end)>>1;
        int l=tree[node<<1];
        if(l>=num) return query(node<<1,start,m,num);
        else return query(node<<1|1,m+1,end,num-l);
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int h=(int)Math.ceil(Math.log(n)/Math.log(2));
        tree=new int[1<<h+1];
        StringBuilder sb=new StringBuilder();
        int Q=Integer.parseInt(br.readLine());
        for(int i=1;i<=Q;i++){
            st=new StringTokenizer(br.readLine());
            int q=Integer.parseInt(st.nextToken());
            if(q==1){
                int val=Integer.parseInt(st.nextToken());
                update(1,1,n,val,1);
            }else{
                int num=Integer.parseInt(st.nextToken());
                int val= query(1,1,n,num);
                sb.append(val).append("\n");
                update(1,1,n,val,-1);
            }
        }
        System.out.print(sb);
    }
}