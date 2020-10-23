import java.io.*;
import java.util.*;

public class Main {
    static final int size=1000001;
    static int[] tree;
    static int update(int node,int start,int end,int idx,int val){
        if(idx>end || idx<start) return tree[node];
        if(start==end){
            return tree[node]+=val;
        }
        int m=(start+end)>>1;
        return tree[node]=update(node<<1,start,m,idx,val)+update(node<<1|1,m+1,end,idx,val);
    }
    static int getFlavor(int node,int start,int end,int f){
        //리프 노드가 될 때까지 좌/우 탐색
        if(start!=end) {
            int m = (start + end) >> 1;
            int left = query(1, 1, size, start, m);
            if (left >= f) {
                return getFlavor(node << 1, start, m, f);
            } else
                return getFlavor(node << 1 | 1, m + 1, end, f - left);
        }
        return start;
    }
    static int query(int node,int start,int end,int left,int right){
        if(start>right || end<left) return 0;
        if(left<=start&&end<=right) return tree[node];
        int m=(start+end)>>1;
        return query(node<<1,start,m,left,right)+query(node<<1|1,m+1,end,left,right);
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb=new StringBuilder();
        int n=Integer.parseInt(br.readLine());
        tree=new int[4*size];
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            if(a==1){
                // b를 꺼낸다.
                int pick=getFlavor(1,1,size,b);
                sb.append(pick).append("\n");
                update(1,1,size,pick,-1);
            }else{
                int c=Integer.parseInt(st.nextToken());
                update(1,1,size,b,c);
            }
        }
        System.out.print(sb);
    }
}