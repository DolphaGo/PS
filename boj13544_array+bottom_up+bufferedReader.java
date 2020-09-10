import java.io.*;
import java.util.*;
public class Main{
    static int last_ans, n;
    static int[] arr;
    static int[][] tree;
    static void mergeSortTree(int node,int start,int end){
        int m=(start+end)>>1;
        int s=start;
        int e=m+1;
        tree[node]=new int[end-start+1];
        int idx=0;
        while(s<=m&&e<=end){
            if(arr[s]<arr[e]) tree[node][idx++]=arr[s++];
            else tree[node][idx++]=arr[e++];
        }
        if(s>m) while(e<=end) tree[node][idx++]=arr[e++];
        if(e>end) while(s<=m) tree[node][idx++]=arr[s++];

        for(int i=start;i<=end;i++){
            arr[i]=tree[node][i-start];
        }
    }
    static void merge(int node,int start,int end){
        if(start!=end){
            int m=(start+end)>>1;
            merge(node*2,start,m);
            merge(node*2+1,m+1,end);
            mergeSortTree(node,start,end);
        }else tree[node]=new int[]{arr[start]};
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

    static int query(int node,int start,int end,int left,int right,int k){
        if(start>right || end < left) return 0;

        if(left<=start && end<=right){
            int idx=upperBound(node,k);
            return tree[node].length-idx;
        }
        int m=(start+end)>>1;
        return query(node*2,start,m,left,right,k)+query(node*2+1, m+1,end,left,right,k);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n=Integer.parseInt(br.readLine());
        arr=new int[n+1];
        st=new StringTokenizer(br.readLine());

        for(int i=1;i<=n;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }
        int h=(int)Math.ceil(Math.log(n)/Math.log(2))+1;
        tree=new int[1<<h][];
        merge(1,1,n);

        StringBuilder sb=new StringBuilder();
        int m=Integer.parseInt(br.readLine());
        last_ans=0;
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken())^last_ans;
            int b=Integer.parseInt(st.nextToken())^last_ans;
            int c=Integer.parseInt(st.nextToken())^last_ans;
            sb.append(last_ans=query(1,1,n,a,b,c)).append("\n");
        }
        System.out.print(sb);
    }
}