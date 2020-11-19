import java.io.*;
import java.util.*;

public class Main {
    static long[] tree;
    static long query(int node,int start,int end,int left,int right){
        if(left>end || right < start) return 0;
        if(left<=start && end<=right){
            return tree[node];
        }
        int m=(start+end)>>1;
        return query(node<<1,start,m,left,right)+query(node<<1|1,m+1,end,left,right);
    }
    static void update(int node,int start,int end,int idx){
        if(idx>end || idx<start) return;

        tree[node]+=1;
        if(start!=end){
            int m=(start+end)>>1;
            update(node<<1,start,m,idx);
            update(node<<1|1,m+1,end,idx);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        HashMap<Integer,Integer> map=new HashMap<>();
        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            int val=Integer.parseInt(st.nextToken());
            map.put(val,i);
        }
        st=new StringTokenizer(br.readLine());
        long answer=0;
        tree=new long[4*n];
        for(int i=1;i<=n;i++){
            int val=Integer.parseInt(st.nextToken());
            int idx=map.get(val);
            answer+=query(1,1,n,idx,n);
            update(1,1,n,idx);
        }
        System.out.println(answer);
    }
}