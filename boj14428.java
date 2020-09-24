import java.io.*;
import java.util.*;

public class Main {
    static int[] arr,tree;
    static int init(int node,int start,int end){
        if(start==end){
            return tree[node]=start;
        }
        int m=(start+end)>>1;
        int l_idx=init(node*2,start,m);
        int r_idx=init(node*2+1,m+1,end);
        if(arr[l_idx]>arr[r_idx]) return tree[node]=r_idx;
        else if(arr[l_idx]<arr[r_idx]) return tree[node]=l_idx;
        else return tree[node]=Math.min(l_idx,r_idx);
    }
    static int query(int node,int start,int end,int left,int right){
        if(left>end || right<start) return -1; //범위를 벗어났을 때

        if(left<=start && end<=right) return tree[node];

        int m=(start+end)>>1;
        int l=query(node*2,start,m,left,right);
        int r=query(node*2+1,m+1,end,left,right);
        if(l==-1) return r;
        else if(r==-1) return l;
        else {
            if (arr[l] > arr[r]) return r;
            else return l;
        }
    }

    static int update(int node,int start,int end,int idx,int val){
        if(start>idx || end<idx) return tree[node];

        //leaf-node - Top-down 방식
        if(start==end){
            arr[start]=val;
            return tree[node];
        }

        //리프노드가 아니면
        int m=(start+end)>>1;
        int l=update(node*2,start,m,idx,val);
        int r=update(node*2+1,m+1,end,idx,val);
        if(arr[l]>arr[r]) return tree[node]=r;
        else return tree[node]=l;
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