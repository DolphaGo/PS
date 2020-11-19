import java.io.*;
import java.util.*;

public class Main {
    static int[] tree;
    static int update(int node,int start,int end,int i,int diff){
        if(i>end || i<start) return tree[node];
        if(start==end){
            return tree[node]+=diff;
        }
        int m=(start+end)>>1;
        return tree[node]=update(node<<1,start,m,i,diff)+update(node<<1|1,m+1,end,i,diff);
    }
    static int query(int node,int start,int end,int i){
        if(start==end) return start;
        int m=(start+end)>>1;
        int l=tree[node<<1];

        if(l>=i) return query(node<<1,start,m,i);
        else return query(node<<1|1,m+1,end,i-l);
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        int h=(int)Math.ceil(Math.log(n)/Math.log(2));
        tree=new int[1<<h+1];

        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) {
            int val=Integer.parseInt(st.nextToken());
            update(1,1,n,i,val);
        }

        StringBuilder sb=new StringBuilder();
        int m=Integer.parseInt(br.readLine());
        for(int Q=0;Q<m;Q++){
            st=new StringTokenizer(br.readLine());
            int q=Integer.parseInt(st.nextToken());
            int i=Integer.parseInt(st.nextToken());
            if(q==1){ //부대 인원 업데이트
                int diff=Integer.parseInt(st.nextToken());
                update(1,1,n,i,diff);
            }else{ // i번째 병사가 속한 부대
                sb.append(query(1,1,n,i)).append("\n");
            }
        }
        System.out.print(sb);
    }
}