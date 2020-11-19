import java.io.*;
import java.util.*;

public class Main {
    static int n,k;
    static long[] arr;
    static long[][] tree;
    static long getMedian(int left,int right){
        long s=0;
        long e=65535;
        while(s<e){
            long val=(s+e)>>1;
            int res=query(1,1,n,left,right,val); // val보다 큰 개수
            if(res>k/2) s=val+1; //만약 k/2보다 크다면 s를 확 늘리자.
            else e=val;
        }
        return e;
    }
    static int query(int node,int start,int end,int left,int right,long val){
        if(left>end || right<start) return 0;
        if(left<=start && end<=right){
            int s=0;
            int e=tree[node].length;
            while(s<e){
                int m=(s+e)>>1;
                if(tree[node][m]<=val) s=m+1;
                else e=m;
            }
            return tree[node].length-e;
        }
        int m=(start+end)>>1;
        return query(node<<1,start,m,left,right,val)+query(node<<1|1,m+1,end,left,right,val);
    }
    static void merge(int node,int start,int end){
        tree[node]=new long[end-start+1];

        int mid=(start+end)>>1;
        int s=start;
        int e=mid+1;

        int idx=0;
        while(s<=mid&&e<=end){
            if(arr[s]<arr[e]) tree[node][idx++]=arr[s++];
            else tree[node][idx++]=arr[e++];
        }
        while(s<=mid) tree[node][idx++]=arr[s++];
        while(e<=end) tree[node][idx++]=arr[e++];

        for(int i=start;i<=end;i++) arr[i]=tree[node][i-start];
    }
    static void mergeSortTree(int node,int start,int end){
        if(start!=end){
            int m=(start+end)>>1;
            mergeSortTree(node<<1,start,m);
            mergeSortTree(node<<1|1,m+1,end);
            merge(node,start,end);
        }else tree[node]=new long[]{arr[start]};
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());
        tree=new long[4*n][];

        arr=new long[n+1];
        for(int i=1;i<=n;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }
        mergeSortTree(1,1,n);

        long answer=0;
        for(int i=1;i<=n-k+1;i++){
            answer+=getMedian(i,i+k-1);
        }
        System.out.println(answer);
    }
}