import java.io.*;
import java.util.*;
public class Main {
    static int[] arr;
    static int[][] tree;
    static void conquer(int node,int st,int en){
        tree[node]=new int[en-st+1];
        int idx=0;
        int m=(st+en)>>1;
        int s=st;
        int e=m+1;
        while(s<=m && e<=en){
            if(arr[s]<arr[e]) tree[node][idx++]=arr[s++];
            else tree[node][idx++]=arr[e++];
        }

        while(s<=m) tree[node][idx++]=arr[s++];
        while(e<=en) tree[node][idx++]=arr[e++];

        for(int i=st;i<=en;i++) arr[i]=tree[node][i-st];
    }
    static void divide(int node, int st, int en){
        if(st!=en){
            int m=(st+en)>>1;
            divide(node*2,st,m);
            divide(node*2+1,m+1,en);
            conquer(node,st,en);
        }else tree[node]=new int[]{arr[st]};
    }
    static int upperBound(int[] arr, int x){
        int s=0;
        int e=arr.length;
        while(s<e){
            int m=(s+e)>>1;
            if(arr[m]<=x) s=m+1;
            else e=m;
        }
        return e; // x값 이하의 개수
    }

    // query : val값 이하의 개수들의 합
    static int query(int node,int start,int end,int left,int right,int val){
        if(right < start || end < left) return 0;

        if(left<=start && end<= right){
            return upperBound(tree[node],val);
        }

        int m=(start+end)>>1;
        return query(node*2,start,m,left,right,val)+query(node*2+1,m+1,end,left,right,val);

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int q=Integer.parseInt(st.nextToken());
        st=new StringTokenizer(br.readLine());
        arr=new int[n+1];
        for(int i=1;i<=n;i++) arr[i]=Integer.parseInt(st.nextToken());

        tree=new int[4*n][];
        divide(1,1,n);

        StringBuilder sb=new StringBuilder();
        for(int i=0;i<q;i++){
            st=new StringTokenizer(br.readLine());
            int left=Integer.parseInt(st.nextToken());
            int right=Integer.parseInt(st.nextToken());
            int k=Integer.parseInt(st.nextToken());

            int s= (int)-1e9;
            int e= (int) 1e9;
            //목적 : query 결과가 c개 이상인 첫번째 수 찾기(lowerBound 개념)
            while(s<e){
                int m=(s+e)>>1;
                if(query(1,1,n,left,right,m)<k) s=m+1;
                else e=m;
            }
            sb.append(e).append("\n");
        }
        System.out.print(sb);
    }
}