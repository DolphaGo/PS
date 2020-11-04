import java.io.*;
import java.util.*;

public class Main {
    static final int MAX=4;
    static int[] tree,lazy;
    static void lazyUpdate(int node,int start,int end){
        if(lazy[node]!=0){
            tree[node]+=lazy[node]*(end-start+1);
            if(start!=end){
                lazy[node<<1]+=lazy[node];
                lazy[node<<1|1]+=lazy[node];
            }
            lazy[node]=0;
        }
    }
    static int update(int node,int start,int end,int left,int right){
        lazyUpdate(node,start,end);
        if(left>end || start>right) return tree[node];
        if(left<=start&&end<=right){
            tree[node]+=(end-start+1);
            if(start!=end){
                lazy[node<<1]+=1;
                lazy[node<<1|1]+=1;
            }
            return tree[node];
        }
        int m=(start+end)>>1;
        return tree[node]=update(node<<1,start,m,left,right)+update(node<<1|1,m+1,end,left,right);
    }
    static int query(int node,int start,int end,int idx){
        lazyUpdate(node,start,end);
        if(idx>end || start>idx) return 0;
        if(start==end){
            int ret=tree[node];
            tree[node]=0;
            return ret;
        }
        int m=(start+end)>>1;
        return query(node<<1,start,m,idx)+query(node<<1|1,m+1,end,idx);
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        int h=(int)Math.ceil(Math.log(MAX)/Math.log(2));
        tree=new int[1<<h+1];
        lazy=new int[1<<h+1];

        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++){
            st=new StringTokenizer(br.readLine());
            int l=Integer.parseInt(st.nextToken());
            int r=Integer.parseInt(st.nextToken());
            sb.append(query(1,1,MAX,l)+query(1,1,MAX,r)).append("\n");
            update(1,1,MAX,l+1,r-1);
        }
        System.out.print(sb);
    }
}