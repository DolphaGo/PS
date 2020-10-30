import java.io.*;
import java.util.*;

public class Main {
    static int[][] tree;
    static int[] update(int node,int start,int end,int idx,int val){
        if(idx<start || idx>end) return tree[node];
        if(start==end){
            tree[node][0]=idx;
            tree[node][1]=val;
            return tree[node];
        }
        int m=(start+end)>>1;
        int[] l=update(node<<1,start,m,idx,val);
        int[] r=update(node<<1|1,m+1,end,idx,val);
        if(l[1]<r[1]) return tree[node]=l;
        else if(l[1]>r[1]) return tree[node]=r;
        else{
            if(l[0]>r[0]) return tree[node]=r;
            else return tree[node]=l;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        int h=(int)Math.ceil(Math.log(n)/Math.log(2));
        tree=new int[1<<h+1][2];

        st=new StringTokenizer(br.readLine());

        for(int i=1;i<=n;i++){
            int val=Integer.parseInt(st.nextToken());
            update(1,1,n,i,val);
        }
        int m=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int q=Integer.parseInt(st.nextToken());
            if(q==1){
                int idx=Integer.parseInt(st.nextToken());
                int v=Integer.parseInt(st.nextToken());
                update(1,1,n,idx,v);
            }else{
                sb.append(tree[1][0]).append("\n");
            }
        }
        System.out.print(sb);
    }
}