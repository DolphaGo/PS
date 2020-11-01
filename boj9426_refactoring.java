import java.io.*;
import java.util.*;

public class Main{
    static final int MAX=1<<16;
    static long[] tree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        for(int i = 0; i< n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        long answer = 0;
        tree = new long[4*MAX];
        for(int i=0;i<k-1;i++) update(1,0,MAX,arr[i],1);

        for(int i=k-1;i<n;i++){
            update(1,0,MAX, arr[i],1);
            answer+=query(1,0,MAX,(k+1)/2);
            update(1,0,MAX, arr[i-k+1],-1);
        }
        System.out.println(answer);
    }
    static void update(int node,int start,int end,int idx,int val){
        if(idx<start ||idx >end) return;

        tree[node]+=val;
        if(start!=end){
            int m=(start+end)>>1;
            update(node<<1,start,m,idx,val);
            update(node<<1|1,m+1,end,idx,val);
        }
    }
    static long query(int node,int start,int end,long k){
        int m=(start+end)>>1;
        if(start==end) return start;
        if(tree[node<<1] >= k) return query(node<<1,start,m,k);
        return query(node<<1|1,m+1,end,k-tree[node<<1]);
    }
}
