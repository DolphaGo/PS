import java.io.*;
import java.util.*;

public class Main {
    static int[] arr,tree;
    static int init(int node,int start,int end){
        if(start==end){
            return tree[node]=arr[start];
        }
        int m=(start+end)>>1;
        return tree[node]=Math.min(init(node*2,start,m),init(node*2+1,m+1,end));
    }
    static int query(int node,int start,int end,int left,int right){
        if(left>end || right<start) return Integer.MAX_VALUE;

        if(left<=start && end<=right){
            return tree[node];
        }
        int m=(start+end)>>1;
        return Math.min(query(node*2,start,m,left,right),query(node*2+1,m+1,end,left,right));
    }

    static void update(int node,int start,int end,int idx,int val){
        if(start>idx || end<idx) return;
        //leaf-node
        if(start==end){
            tree[node]=val;
            return;
        }

        int m=(start+end)>>1;
        update(node*2,start,m,idx,val);
        update(node*2+1,m+1,end,idx,val);
        tree[node]=Math.min(tree[node*2],tree[node*2+1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        st=new StringTokenizer(br.readLine());
        arr=new int[n+1];
        for(int i=1;i<=n;i++) arr[i]=Integer.parseInt(st.nextToken());

        int h=(int)Math.ceil(Math.log(n)/Math.log(2));
        tree=new int[1<<h+1];
        init(1,1,n);

        StringBuilder sb=new StringBuilder();

        int m=Integer.parseInt(br.readLine());
        for(int k=0;k<m;k++){
            st=new StringTokenizer(br.readLine());
            int q=Integer.parseInt(st.nextToken());
            int i=Integer.parseInt(st.nextToken());
            if(q==1){
                int v=Integer.parseInt(st.nextToken());
                update(1,1,n,i,v);
            }else{
                int j=Integer.parseInt(st.nextToken());
                sb.append(query(1,1,n,i,j)).append("\n");
            }
        }
        System.out.print(sb);
    }
}