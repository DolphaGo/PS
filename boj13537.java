import java.io.*;
import java.util.*;
public class Main{
    static int n;
    static int[] arr;
    static int[][] tree;
    static void mergeTreeSort(int node,int st,int en){
        tree[node]=new int[en-st+1];
        int s=st;
        int m=(st+en)>>1;
        int e=m+1;
        int idx=0;
        while(s<=m&&e<=en){
            if(arr[s]<arr[e]) tree[node][idx++]=arr[s++];
            else tree[node][idx++]=arr[e++];
        }
        while(s<=m) tree[node][idx++]=arr[s++];
        while(e<=en) tree[node][idx++]=arr[e++];

        for(int i=st;i<=en;i++) arr[i]=tree[node][i-st];
    }

    static void merge(int node,int st,int en){
        if(st!=en){
            int m=(st+en)>>1;
            merge(node*2,st,m);
            merge(node*2+1,m+1,en);
            mergeTreeSort(node,st,en);
        }else tree[node]=new int[]{arr[st]};
    }
    static int upperBound(int node,int k){
        int s=0;
        int e=tree[node].length;
        while(s<e){
            int m=(s+e)>>1;
            if(tree[node][m]<=k) s=m+1;
            else e=m;
        }
        return e;
    }

    static int query(int node,int st,int en,int l,int r,int k){
        if(st>r || en<l) return 0;
        if(l<=st && en<=r){
            int idx=upperBound(node,k);
            return tree[node].length-idx;
        }
        int m=(st+en)>>1;
        return query(node*2,st,m,l,r,k)+query(node*2+1,m+1,en,l,r,k);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n=Integer.parseInt(br.readLine());
        arr=new int[n+1];
        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) arr[i]=Integer.parseInt(st.nextToken());

        int h=(int)Math.ceil(Math.log(n)/Math.log(2))+1;
        tree=new int[1<<h][];
        merge(1,1,n);

        int m=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            sb.append(query(1, 1, n, a, b, k)).append("\n");
        }
        System.out.print(sb);
    }
}